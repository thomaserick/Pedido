package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pedido.model.CampoVazio;
import com.example.pedido.model.Clientes;
import com.example.pedido.model.Pedido;
import com.example.pedido.model.PedidoItem;
import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class FormItemPedidos extends AppCompatActivity {


    TextView etPedido;
    Database db;
    CampoVazio campoVazio;
    ArrayAdapter adapter;
    ArrayList<PedidoItem> lv_itemPedido;
    Button btIncalt, btAddItens;
    Pedido pedidoSel;
    PedidoItem pedidoItem;
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_item_pedidos);

        pedidoItem = new PedidoItem();

        etPedido = (TextView) findViewById(R.id.et_pedido);


        btIncalt = (Button) findViewById(R.id.bt_incalt);
        btAddItens = (Button) findViewById(R.id.bt_addItens);


        campoVazio = new CampoVazio();

        pedidoSel = (Pedido) getIntent().getSerializableExtra("select-pedido");


        etPedido.setText(Long.toString(pedidoSel.getCodigo()));

        pedidoItem.setCodigo(pedidoSel.getCodigo());

        lista = (ListView) findViewById(R.id.lv_itemPedido);
        registerForContextMenu(lista);

        listaItemPedido();


        btAddItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FormItemPedidos.this, FormAddItemPedido.class);
                i.putExtra("pedido", pedidoSel);
                startActivity(i);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id){
                pedidoItem = (PedidoItem) adapter.getItemAtPosition(position);
                return false;
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Este Produto");
        ((MenuItem) menuDelete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                db = new Database(FormItemPedidos.this);
                db.deletarItemPedido(pedidoItem);
                db.close();
                listaItemPedido();
                return true;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        listaItemPedido();
    }


    //Lista os Produtos
    public void listaItemPedido() {
        db = new Database(FormItemPedidos.this);
        lv_itemPedido = db.getAllItemPedidos(pedidoItem);
        db.close();

        if (lv_itemPedido != null) {
            adapter = new ArrayAdapter<PedidoItem>(FormItemPedidos.this, android.R.layout.simple_list_item_1, lv_itemPedido);
            lista.setAdapter(adapter);
        }


    }


}
