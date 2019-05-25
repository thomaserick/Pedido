package com.example.pedido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pedido.model.Clientes;

public class FormClientes extends AppCompatActivity {

    EditText etNome,etEndereco,etEndnum,etTelefone;
    Button btnIncaltCliente;
    Clientes editarCliente,cliente;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_clientes);
        setTitle("Cadastro de Clientes");


        cliente = new Clientes();
        db = new Database(FormClientes.this);


        etNome = (EditText)findViewById(R.id.et_nome);
        etEndereco = (EditText)findViewById(R.id.et_endereco);
        etEndnum = (EditText)findViewById(R.id.et_endnum);
        etTelefone = (EditText)findViewById(R.id.et_telefone);
        btnIncaltCliente = (Button)findViewById(R.id.btn_IncaltCliente);

        if(editarCliente !=null){
            btnIncaltCliente.setText("Modificar");
            setTitle("Alteracao de Clienttes");

        } else {

            btnIncaltCliente.setText("Cadastrar");
            setTitle("Cadastro de Clientes");
        }



    }
}
