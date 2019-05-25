package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashbordActivity extends AppCompatActivity {

        Button btClientes,btProdutos,btPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        setTitle("Menu Pedido");


        btClientes=(Button)findViewById(R.id.bt_clientes);
        btProdutos=(Button)findViewById(R.id.bt_produtos);
        btPedidos=(Button)findViewById(R.id.bt_pedidos);

        btClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(DashbordActivity.this, ClienteActivity.class);
                startActivity(i);


            }
        });
        btProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(DashbordActivity.this, ProdutoActivity.class);
                startActivity(i);

            }
        });

        btPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(DashbordActivity.this, PedidoActivity.class);
                startActivity(i);

            }
        });



    }
}
