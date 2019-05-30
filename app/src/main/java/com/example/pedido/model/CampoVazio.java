package com.example.pedido.model;

import android.text.TextUtils;

public class CampoVazio {

    private String dados;
    private Boolean result;
    private float dec;


    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }


    public boolean isBlank(String value) {
        return (value == null || value.equals("") || value.equals("null") || value.trim().equals(""));
    }

    public boolean validarFormInt(String dados) {

        if (!isBlank(dados)) {
            float dec = Float.parseFloat(dados);

            if (dec <= 0) {
                return true;
            } else {
                return false;
            }
        } else return true;

    }

}
