package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.ContextMenu;

import com.example.pedido.model.Clientes;
import com.example.pedido.model.Pedido;

import java.util.ArrayList;

public class PedidoActivity extends AppCompatActivity {

    Button btnCadPedidos;

    ArrayList<Pedido> lv_pedidos;
    ListView lista;
    Pedido pedido;
    ArrayAdapter adapter;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        setTitle("Pedidos");


        btnCadPedidos = (Button) findViewById(R.id.btn_CadPedidos);
        lista = (ListView) findViewById(R.id.lv_pedidos);

        registerForContextMenu(lista);

        btnCadPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PedidoActivity.this, FormHeadPedidos.class);
                startActivity(i);

            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Pedido pedidoSel = (Pedido) adapter.getItemAtPosition(position);

                Intent i = new Intent(PedidoActivity.this, FormItemPedidos.class);
                i.putExtra("select-pedido", pedidoSel);
                startActivity(i);


            }
        });


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                pedido = (Pedido) adapter.getItemAtPosition(position);
                return false;
            }
        });




    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este Pedido");
        ((MenuItem) menuDelete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                db = new Database(PedidoActivity.this);
                db.deletarPedido(pedido);
                db.close();
                listaPedido();
                return true;
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        listaPedido();
    }


    public void listaPedido() {
        db = new Database(PedidoActivity.this);
        lv_pedidos = db.getAllPedidos();
        db.close();

        if (lv_pedidos != null) {
            adapter = new ArrayAdapter<Pedido>(PedidoActivity.this, android.R.layout.simple_list_item_1, lv_pedidos);
            lista.setAdapter(adapter);
        }

    }

}
