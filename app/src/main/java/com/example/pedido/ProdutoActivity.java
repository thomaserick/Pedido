package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProdutoActivity extends AppCompatActivity {

    Button btnCadProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        btnCadProdutos =(Button)findViewById(R.id.btn_CadProdutos);

        btnCadProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(ProdutoActivity.this, FormProdutos.class);
                startActivity(i);

            }
        });
    }
}
