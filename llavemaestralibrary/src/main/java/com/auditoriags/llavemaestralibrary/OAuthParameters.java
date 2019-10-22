package com.auditoriags.llavemaestralibrary;

/**
 * Proyecto: OAuth
 * Autor: Emmanuel Rangel Reyes
 * Fecha: 01/10/2019.
 * Empresa: Grupo Salinas
 * Area: Auditoria Sistemas y Monitoreo de Alarmas
 */
public class OAuthParameters {

    private String url;
    private String responseType;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private String acrValues;

    public OAuthParameters() {
    }

    public OAuthParameters(String url, String responseType, String clientId, String clientSecret, String redirectUri, String scope, String acrValues) {
        this.url = url;
        this.responseType = responseType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.scope = scope;
        this.acrValues = acrValues;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAcrValues() {
        return acrValues;
    }

    public void setAcrValues(String acrValues) {
        this.acrValues = acrValues;
    }
}
