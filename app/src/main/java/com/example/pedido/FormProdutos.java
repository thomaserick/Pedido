package com.example.pedido;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedido.model.Produtos;

public class FormProdutos extends AppCompatActivity {

    EditText etCodgio, etDescricao, etQuantidade, etPreco;
    Button btnIncaltProduto;
    Produtos editarProduto, produto;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produtos);


        produto = new Produtos();
        db = new Database(FormProdutos.this);

        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("select-produto");

        etCodgio = (EditText)findViewById(R.id.et_CodigoProduto);
        etDescricao=(EditText)findViewById(R.id.et_DescricaoProduto);
        etQuantidade=(EditText)findViewById(R.id.et_QuantidadeProduto);
        etPreco=(EditText)findViewById(R.id.et_precoProduto);
        btnIncaltProduto=(Button)findViewById(R.id.btn_IncaltProduto);


        if(editarProduto !=null){
          btnIncaltProduto.setText("Modificar");
            setTitle("Alteracao de Produtos");

          etCodgio.setText(Integer.toString(editarProduto.getCodigo()));
          etDescricao.setText(editarProduto.getDescricao());
          etQuantidade.setText(Integer.toString(editarProduto.getQuantidade()));
          etPreco.setText(Float.toString(editarProduto.getPreco()));

          produto.setId(editarProduto.getId());

        } else {

            btnIncaltProduto.setText("Cadastrar");
            setTitle("Cadastro de Produtos");
        }

        btnIncaltProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                produto.setCodigo(Integer.parseInt(etCodgio.getText().toString()));
                produto.setDescricao(etDescricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(etQuantidade.getText().toString()));
                produto.setPreco(Float.parseFloat(etPreco.getText().toString()));

                if(btnIncaltProduto.getText().toString().equals("Cadastrar")){


                    Boolean res = db.insertProdutos(produto);

                    if (res == true) {
                        Toast.makeText(FormProdutos.this, "Produto inserido com Sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormProdutos.this, "Erro; Nao foi possivel inserir o Produto.", Toast.LENGTH_SHORT).show();
                    }

                     db.close();

                }else {

                    Boolean res = db.alterarProduto(produto);

                    if (res == true) {
                        Toast.makeText(FormProdutos.this, "Produto Alterado com Sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormProdutos.this, "Erro; Nao foi possivel Alterar o Produto.", Toast.LENGTH_SHORT).show();
                    }

                    db.close();
                }

            }
        });
    }

}
