package com.example.pedido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pedido.model.CampoVazio;
import com.example.pedido.model.Clientes;
import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class FormItemPedidos extends AppCompatActivity {


    Database db;
    CampoVazio campoVazio;
    ArrayAdapter adapter;
    ArrayList<Produtos> sp_produtos;
    Spinner spProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_item_pedidos);

        spProdutos = (Spinner) findViewById(R.id.sp_produtos);

        listaProduto();

    }


    //Lista os Produtos
    public void listaProduto() {
        db = new Database(FormItemPedidos.this);
        sp_produtos = db.getLista();
        db.close();

        if (sp_produtos != null) {
            adapter = new ArrayAdapter<Produtos>(FormItemPedidos.this, android.R.layout.simple_spinner_dropdown_item, sp_produtos);
            spProdutos.setAdapter(adapter);
        }

    }


}
