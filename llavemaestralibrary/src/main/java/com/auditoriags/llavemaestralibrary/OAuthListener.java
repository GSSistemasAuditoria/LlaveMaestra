package com.auditoriags.llavemaestralibrary;

/**
 * Proyecto: OAuth
 * Autor: Emmanuel Rangel Reyes
 * Fecha: 01/10/2019.
 * Empresa: Grupo Salinas
 * Area: Auditoria Sistemas y Monitoreo de Alarmas
 */
public interface OAuthListener {

    void oAuthSuccess(String result);
    void oAuthFailed(String result);
}
