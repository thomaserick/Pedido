package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PedidoActivity extends AppCompatActivity {

    Button btnCadPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        setTitle("Pedidos");


        btnCadPedidos = (Button)findViewById(R.id.btn_CadPedidos) ;
        btnCadPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(PedidoActivity.this, FormPedidos.class);
                startActivity(i);

            }
        });

    }
}
