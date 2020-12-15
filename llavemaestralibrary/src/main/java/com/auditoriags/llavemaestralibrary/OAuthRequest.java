package com.auditoriags.llavemaestralibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Proyecto: OAuth
 * Autor: Emmanuel Rangel Reyes
 * Fecha: 01/10/2019.
 * Empresa: Grupo Salinas
 * Area: Auditoria Sistemas y Monitoreo de Alarmas
 */
public class OAuthRequest extends AsyncTask<String,String,String> {

    private ProgressDialog statusDialog;
    private Context context;
    private String url;
    private String token;
    private String metodo = "GET";
    private String textoDialogo = "Conectando";
    private OAuthListener oAuthListener;

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public void setTextoDialogo(String textoDialogo) {
        this.textoDialogo = textoDialogo;
    }

    public OAuthRequest(Context context, String url, String token, OAuthListener oAuthListener){
        this.context = context;
        this.url = url;
        if(token.startsWith(OAuthConstants.TOKEN_TYPE)) {
            this.token = token;
        }else{
            this.token = OAuthConstants.TOKEN_TYPE + " " + token;
        }
        this.oAuthListener = oAuthListener;
    }

    protected void onPreExecute() {
        statusDialog = new ProgressDialog(context);
        statusDialog.setMessage(textoDialogo);
        statusDialog.setCancelable(false);
        /*if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP ||
                Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) {
            statusDialog.setIndeterminate(true);
            Drawable drawable = new ProgressBar(context).getIndeterminateDrawable().mutate();
            drawable.setColorFilter(ContextCompat.getColor(context, R.color.yellow_gs),
                    PorterDuff.Mode.SRC_IN);
            statusDialog.setIndeterminateDrawable(drawable);
        }else{
            statusDialog.setIndeterminate(false);
        }//*/
        statusDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            return conexionOAuth(url,token);
        } catch (NoSuchAlgorithmException e) {
            Log.e("Error ADA:",Log.getStackTraceString(e));
            return OAuthConstants.ERROR_TAG + e.getMessage();
        } catch (IOException e) {
            Log.e("Error ADA:",Log.getStackTraceString(e));
            return OAuthConstants.ERROR_TAG + e.getMessage();
        } catch (KeyManagementException e) {
            Log.e("Error ADA:",Log.getStackTraceString(e));
            return OAuthConstants.ERROR_TAG + e.getMessage();
        } catch (Exception e) {
            Log.e("Error ADA:",Log.getStackTraceString(e));
            return OAuthConstants.ERROR_TAG + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        statusDialog.dismiss();
        if(result != null){
            if(!result.startsWith(OAuthConstants.ERROR_TAG)){
                oAuthListener.oAuthSuccess(result);
            }else{
                oAuthListener.oAuthFailed(result);
            }
        }else{
            oAuthListener.oAuthFailed("No se pudo conectar a " + url);
        }
    }

    @Override
    public void onProgressUpdate(String... values) {
        statusDialog.setMessage(values[0]);
    }

    private String conexionOAuth(String urlStr, String basicAuth) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        URL url = new URL(urlStr);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", basicAuth);
        conn.setReadTimeout(7000);
        conn.setConnectTimeout(7000);
        conn.setRequestMethod(metodo);
        conn.setDoInput(true);
        conn.connect();
        String str = IOUtils.toString(conn.getInputStream());
        conn.disconnect();
        return str;
    }
}
