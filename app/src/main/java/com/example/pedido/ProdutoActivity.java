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
import android.widget.ListView;

import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

    ListView lista;
    Database db;
    ArrayList<Produtos> lv_produtos;
    Produtos produto;
    ArrayAdapter adapter;

    Button btnCadProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        setTitle("Produtos");
        btnCadProdutos =(Button)findViewById(R.id.btn_CadProdutos);

        lista = (ListView)findViewById(R.id.lv_produtos);

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Produtos produtoSel = (Produtos) adapter.getItemAtPosition(position);

                Intent i  = new Intent(ProdutoActivity.this, FormProdutos.class);
                i.putExtra("select-produto", produtoSel);
                startActivity(i);
            }
        });

        btnCadProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(ProdutoActivity.this, FormProdutos.class);
                startActivity(i);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id){
                produto = (Produtos) adapter.getItemAtPosition(position);
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
                db = new Database(ProdutoActivity.this);
                db.deletarProduto(produto);
                db.close();
                listaProduto();
                return true;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        listaProduto();
    }

    public void listaProduto(){
        db = new Database(ProdutoActivity.this);
        lv_produtos = db.getLista();
        db.close();

        if(lv_produtos !=null){
            adapter = new ArrayAdapter<Produtos>(ProdutoActivity.this,android.R.layout.simple_list_item_1,lv_produtos);
            lista.setAdapter(adapter);
        }

    }


}
