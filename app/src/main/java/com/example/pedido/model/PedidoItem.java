package com.example.pedido.model;

import org.w3c.dom.Text;

import java.io.Serializable;


public class PedidoItem implements Serializable {

    private Long codigo;
    private int item;
    private int idProduto;
    private String Produto;
    private int quantidade;
    private Double valorUnit;
    private Double valorTotal;


    @Override
    public String toString() {
        return " 0000" + item + " - " + Produto + " - QTD: " + quantidade +" - Total: R$ " + valorTotal;

    }


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

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}