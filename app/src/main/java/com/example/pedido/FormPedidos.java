package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pedido.model.CampoVazio;
import com.example.pedido.model.Clientes;
import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class FormPedidos extends AppCompatActivity {

    String[] formapg = new String[]{"Dinheiro", "Cartão", "Cheque"};
    Database db;
    CampoVazio campoVazio;
    ArrayAdapter adapter;
    ArrayList<Clientes> sp_clientes;
    Spinner spClientes, spFormapg;
    Button btaddItens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pedidos);
        setTitle("Cadastrar Pedido");

        spClientes = (Spinner) findViewById(R.id.sp_clientes);
        spFormapg = (Spinner) findViewById(R.id.sp_formpg);

        btaddItens = (Button) findViewById(R.id.bt_addItens);

        listaCliente();
        ListaFormapg();


        btaddItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormPedidos.this, FormItemPedidos.class);
                startActivity(i);
            }
        });

    }


    public void listaCliente() {
        db = new Database(FormPedidos.this);
        sp_clientes = db.getAllCliente();
        db.close();

        if (sp_clientes != null) {
            adapter = new ArrayAdapter<Clientes>(FormPedidos.this, android.R.layout.simple_spinner_dropdown_item, sp_clientes);
            spClientes.setAdapter(adapter);
        }

    }

    public void ListaFormapg() {

        adapter = new ArrayAdapter<String>(FormPedidos.this, android.R.layout.simple_spinner_dropdown_item, formapg);
        spFormapg.setAdapter(adapter);

    }


}

