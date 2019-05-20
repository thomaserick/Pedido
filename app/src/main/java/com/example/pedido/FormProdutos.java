package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pedido.BDHelper.ProdutosDb;
import com.example.pedido.model.Produtos;

public class FormProdutos extends AppCompatActivity {

    EditText etCodgio,etDescricao,etQuantidade;
    Button btnIncaltProduto;
    Produtos editarProduto,produto;
    ProdutosDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produtos);

        db = new ProdutosDb(FormProdutos.this);

        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("select-produto");

        etCodgio = (EditText)findViewById(R.id.et_CodigoProduto);
        etDescricao=(EditText)findViewById(R.id.et_DescricaoProduto);
        etQuantidade=(EditText)findViewById(R.id.et_QuantidadeProduto);
        btnIncaltProduto=(Button)findViewById(R.id.btn_IncaltProduto);


        if(editarProduto !=null){
          btnIncaltProduto.setText("Modificar");
        } else {
            btnIncaltProduto.setText("Cadastrar");
        }

        btnIncaltProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                produto.setCodigo(Integer.parseInt(etCodgio.getText().toString()));
                produto.setDescricao(etDescricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(etQuantidade.getText().toString()));

                if(btnIncaltProduto.getText().toString().equals("Cadastrar")){

                     db.insertProdutos(produto);
                     db.close();

                }else {



                }

            }
        });
    }

}
