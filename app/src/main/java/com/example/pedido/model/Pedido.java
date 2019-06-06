package com.example.pedido.model;

import java.io.Serializable;
import java.sql.Date;

public class Pedido implements Serializable {

    private Long codigo;
    private Long idCliente;
    private String Cliente;
    private String formapg;
    private Date data;
    private String status;

    @Override
    public String toString() {
        return " " + codigo + " - Cliente:" + Cliente;

    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getFormapg() {
        return formapg;
    }

    public void setFormapg(String formapg) {
        this.formapg = formapg;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
