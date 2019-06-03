package com.example.pedido.model;

import java.sql.Date;

public class PedidoItem {

    private Long codigo;
    private int item;
    private Long idProduto;
    private Long Produto;
    private int quantidade;
    private int valorUnit;
    private int valorTotal;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getProduto() {
        return Produto;
    }

    public void setProduto(Long produto) {
        Produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(int valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }
}
