package com.auditoriags.llavemaestralibrary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Proyecto: OAuth
 * Autor: Emmanuel Rangel Reyes
 * Fecha: 01/10/2019.
 * Empresa: Grupo Salinas
 * Area: Auditoria Sistemas y Monitoreo de Alarmas
 */
public class OAuth {

    public static void authentication(Activity activity, OAuthParameters oAuthParameters) throws UnsupportedEncodingException, MalformedURLException {
        Uri uri = Uri.parse(oAuthParameters.getUrl()).buildUpon()
                .appendQueryParameter(OAuthConstants.PARAM_RESPONSE_TYPE,oAuthParameters.getResponseType())
        .appendQueryParameter(OAuthConstants.PARAM_CLIENT_ID,oAuthParameters.getClientId())
        .appendQueryParameter(OAuthConstants.PARAM_CLIENT_SECRET,oAuthParameters.getClientSecret())
        .appendQueryParameter(OAuthConstants.PARAM_REDIRECT_URI,oAuthParameters.getRedirectUri())
        .appendQueryParameter(OAuthConstants.PARAM_SCOPE,oAuthParameters.getScope())
        .appendQueryParameter(OAuthConstants.PARAM_ACR_VALUES,oAuthParameters.getAcrValues())
                .build();
        URL url = new URL(URLDecoder.decode(uri.toString(), "UTF-8"));
        String urlLlaveMaestra = url.toString();
        openOAuthURL(activity,urlLlaveMaestra);
    }

    public static void openOAuthURL(Activity activity, String url){
        Uri uri = Uri.parse(url);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        String packageName = CustomTabsHelper.getPackageNameToUse(activity, url);

        CustomTabsIntent customTabsIntent = intentBuilder.build();
        //customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        customTabsIntent.intent.setPackage(packageName);
        customTabsIntent.launchUrl(activity, uri);
    }

    public static String getAccessToken(Uri uri){
        String fragment = uri.getFragment();
        Uri uriArgs = Uri.parse("app://ada?" + fragment);
        String token = uriArgs.getQueryParameter(OAuthConstants.PARAM_ACCESS_TOKEN);
        return token;
    }
}
