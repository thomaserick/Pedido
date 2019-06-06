package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        listaItemPedido();


        btAddItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FormItemPedidos.this, FormAddItemPedido.class);
                i.putExtra("pedido", pedidoSel);
                startActivity(i);

            }
        });


    }


    //Lista os Produtos
    public void listaItemPedido() {
        db = new Database(FormItemPedidos.this);
        lv_itemPedido = db.getAllItemPedidos(pedidoItem);
        db.close();


    }


}
