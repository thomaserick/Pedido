package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pedido.model.Clientes;
import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity {

    ListView lista;
    Database db;
    ArrayList<Clientes> lv_clientes;
    Clientes cliente;
    ArrayAdapter adapter;

    Button btnCadCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        setTitle("Clientes");

        btnCadCliente = (Button) findViewById(R.id.btn_CadClientes);

        lista = (ListView) findViewById(R.id.lv_clientes);

        registerForContextMenu(lista);

        btnCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClienteActivity.this, FormClientes.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Este Produto");
        ((MenuItem) menuDelete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                db = new Database(ClienteActivity.this);
                db.deletarCliente(cliente);
                db.close();
                listaCliente();
                return true;
            }
        });
    }

    public void listaCliente() {
        db = new Database(ClienteActivity.this);
        lv_clientes = db.getAllCliente();
        db.close();

        if (lv_clientes != null) {
            adapter = new ArrayAdapter<Clientes>(ClienteActivity.this, android.R.layout.simple_list_item_1, lv_clientes);
            lista.setAdapter(adapter);
        }

    }

}
