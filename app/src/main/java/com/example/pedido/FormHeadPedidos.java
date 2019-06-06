package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pedido.model.CampoVazio;
import com.example.pedido.model.Clientes;
import com.example.pedido.model.Pedido;

import java.sql.Date;
import java.util.ArrayList;

public class FormHeadPedidos extends AppCompatActivity {

    String[] formapg = new String[]{"Dinheiro", "Cartão", "Cheque"};
    Database db;
    CampoVazio campoVazio;
    ArrayAdapter adapter;
    ArrayList<Clientes> sp_clientes;
    Spinner spClientes, spFormapg;
    Button bt_incalt;
    Pedido pedido;
    Clientes clienteSel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_head_pedidos);
        setTitle("Cadastrar Pedido");


        pedido = new Pedido();

        spClientes = (Spinner) findViewById(R.id.sp_clientes);
        spFormapg = (Spinner) findViewById(R.id.sp_formpg);

        bt_incalt = (Button) findViewById(R.id.bt_incalt);

        listaCliente();
        ListaFormapg();

        spClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clienteSel = (Clientes) spClientes.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spFormapg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String fomapg = (String) spFormapg.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bt_incalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedido.setIdCliente(clienteSel.getId());
                pedido.setCliente(clienteSel.getNome());
                pedido.setFormapg((String) spFormapg.getSelectedItem());
                pedido.setStatus("A");

                Boolean res = db.insertPedidos(pedido);


                if (res == true) {
                    Toast.makeText(FormHeadPedidos.this, "Pedido inserido com Sucesso!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(FormHeadPedidos.this,FormAddItemPedido.class);
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  //Limpa a Tela
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(FormHeadPedidos.this, "Erro: Nao foi possível inserir o Pedido.", Toast.LENGTH_SHORT).show();
                }
                db.close();

            }
        });


    }


    public void listaCliente() {
        db = new Database(FormHeadPedidos.this);
        sp_clientes = db.getAllCliente();
        db.close();

        if (sp_clientes != null) {
            adapter = new ArrayAdapter<Clientes>(FormHeadPedidos.this, android.R.layout.simple_spinner_dropdown_item, sp_clientes);
            spClientes.setAdapter(adapter);
        }

    }

    public void ListaFormapg() {

        adapter = new ArrayAdapter<String>(FormHeadPedidos.this, android.R.layout.simple_spinner_dropdown_item, formapg);
        spFormapg.setAdapter(adapter);

    }


}
