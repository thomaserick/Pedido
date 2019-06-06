package com.example.pedido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pedido.model.CampoVazio;
import com.example.pedido.model.Pedido;
import com.example.pedido.model.PedidoItem;
import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class FormAddItemPedido extends AppCompatActivity {

    EditText etQuantidade, etPreco, etVltotal;
    Database db;
    Spinner spProdutos;
    ArrayAdapter adapter;
    ArrayList<Produtos> sp_produtos;
    Produtos produtoSel;
    Boolean result = false;
    CampoVazio campoVazio;
    Button btAddItens;
    PedidoItem pedidoItem;
    Pedido pedidoSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_item_pedido);


        pedidoItem = new PedidoItem();
        db = new Database(FormAddItemPedido.this);
        campoVazio = new CampoVazio();

        spProdutos = (Spinner) findViewById(R.id.sp_produtos);


        etQuantidade = (EditText) findViewById(R.id.et_quantidade);
        etPreco = (EditText) findViewById(R.id.et_preco);
        etVltotal = (EditText) findViewById(R.id.et_vltotal);
        btAddItens = (Button) findViewById(R.id.bt_addItens);

        pedidoSel = (Pedido) getIntent().getSerializableExtra("pedido");

        spProdutos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                produtoSel = (Produtos) spProdutos.getItemAtPosition(position);
                etPreco.setText(Float.toString(produtoSel.getPreco()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listaProduto();

        btAddItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String qtde = etQuantidade.getText().toString();
                String preco = etPreco.getText().toString();

                result = campoVazio.validarFormInt(qtde);
                if (result == true) {
                    Toast.makeText(FormAddItemPedido.this, "Informe a Quantidade.", Toast.LENGTH_SHORT).show();
                    etQuantidade.requestFocus();
                    return;
                }


                result = campoVazio.validarFormInt(preco);
                if (result == true) {
                    Toast.makeText(FormAddItemPedido.this, "Informe o Preço.", Toast.LENGTH_SHORT).show();
                    etPreco.requestFocus();
                    return;
                }


                pedidoItem.setCodigo(pedidoSel.getCodigo());

                pedidoItem.setItem(1);

                pedidoItem.setIdProduto(produtoSel.getCodigo());
                pedidoItem.setProduto(produtoSel.getDescricao());
                pedidoItem.setQuantidade(Integer.parseInt(qtde));
                pedidoItem.setValorUnit(Double.parseDouble(preco));
                pedidoItem.setValorTotal(pedidoItem.getValorUnit() * pedidoItem.getQuantidade());

                Boolean res = db.insertItemPedido(pedidoItem);

                if (res == true) {
                    Toast.makeText(FormAddItemPedido.this, "Item inserido com Sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(FormAddItemPedido.this, "Erro: Nao foi possível inserir o Item.", Toast.LENGTH_SHORT).show();
                }
                db.close();


            }
        });


    }


    public void listaProduto() {
        db = new Database(FormAddItemPedido.this);
        sp_produtos = db.getLista();
        db.close();

        if (sp_produtos != null) {
            adapter = new ArrayAdapter<Produtos>(FormAddItemPedido.this, android.R.layout.simple_list_item_1, sp_produtos);
            spProdutos.setAdapter(adapter);
        }

    }

    public void vlTotal() {

        int qtde = Integer.valueOf(etQuantidade.getText().toString());
        int vlunit = Integer.valueOf(etPreco.getText().toString());
        int res = qtde * vlunit;

        etVltotal.setText(String.valueOf(res));


    }


}
