package com.example.pedido.model;

import java.io.Serializable;

public class Clientes implements Serializable {

    private long id;
    private String nome;
    private String endereco;
    private int endnum;
    private String telefone;


    @Override
    public String toString(){
        return id + " - " + nome + " - " + endereco;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getEndnum() {
        return endnum;
    }

    public void setEndnum(int endnum) {
        this.endnum = endnum;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
