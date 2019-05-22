package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClienteActivity extends AppCompatActivity {

        Button btnCadCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        btnCadCliente = (Button)findViewById(R.id.btn_CadClientes) ;

        btnCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(ClienteActivity.this, FormClientes.class);
                startActivity(i);

            }
        });
    }
}
