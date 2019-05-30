package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedido.Helper.ClienteDb;
import com.example.pedido.model.CampoVazio;
import com.example.pedido.model.Clientes;

public class FormClientes extends AppCompatActivity {

    EditText etNome, etEndereco, etEndnum, etTelefone;
    Button btnIncaltCliente;
    Clientes editarCliente, cliente;
    Database db;
    Boolean result = false;
    CampoVazio campoVazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_clientes);
        setTitle("Cadastro de Clientes");


        cliente = new Clientes();
        db = new Database(FormClientes.this);

        campoVazio = new CampoVazio();

        Intent intent = getIntent();
        editarCliente = (Clientes) intent.getSerializableExtra("select-cliente");

        etNome = (EditText) findViewById(R.id.et_nome);
        etEndereco = (EditText) findViewById(R.id.et_endereco);
        etEndnum = (EditText) findViewById(R.id.et_endnum);
        etTelefone = (EditText) findViewById(R.id.et_telefone);
        btnIncaltCliente = (Button) findViewById(R.id.btn_IncaltCliente);

        if (editarCliente != null) {
            btnIncaltCliente.setText("Modificar");
            setTitle("Alteracao de Clienttes");

            etNome.setText(editarCliente.getNome());
            etEndereco.setText(editarCliente.getEndereco());
            etEndnum.setText(Integer.toString(editarCliente.getEndnum()));
            etTelefone.setText(editarCliente.getTelefone());

            cliente.setId(editarCliente.getId());

        } else {

            btnIncaltCliente.setText("Cadastrar");
            setTitle("Cadastro de Clientes");
        }

        btnIncaltCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result = campoVazio.isBlank(etNome.getText().toString());
                if (result) {
                    Toast.makeText(FormClientes.this, "Informe o Nome.", Toast.LENGTH_SHORT).show();
                    etNome.requestFocus();
                    return;
                }
                result = campoVazio.isBlank(etEndereco.getText().toString());
                if (result) {
                    Toast.makeText(FormClientes.this, "Informe o Endereço.", Toast.LENGTH_SHORT).show();
                    etEndereco.requestFocus();
                    return;
                }

                result = campoVazio.validarFormInt(etEndnum.getText().toString());
                if (result) {
                    Toast.makeText(FormClientes.this, "Informe o Número.", Toast.LENGTH_SHORT).show();
                    etEndnum.requestFocus();
                    return;
                }

                result = campoVazio.isBlank(etTelefone.getText().toString());
                if (result) {
                    Toast.makeText(FormClientes.this, "Informe o Telefone.", Toast.LENGTH_SHORT).show();
                    etTelefone.requestFocus();
                    return;
                }

                cliente.setNome(etNome.getText().toString());
                cliente.setEndereco(etEndereco.getText().toString());
                cliente.setTelefone(etTelefone.getText().toString());
                cliente.setEndnum(Integer.parseInt(etEndnum.getText().toString()));


                if (btnIncaltCliente.getText().toString().equals("Cadastrar")) {

                    Boolean chkCliente = db.chkCliente(cliente);

                    if (chkCliente == true) {
                        Boolean res = db.insertClientes(cliente);

                        if (res == true) {
                            Toast.makeText(FormClientes.this, "Cliente inserido com Sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FormClientes.this, "Erro; Nao foi possível inserir o Cliente.", Toast.LENGTH_SHORT).show();
                        }
                        db.close();

                    } else {
                        Toast.makeText(FormClientes.this, "Cliente ja cadastrado.", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Boolean res = db.alterarClientes(cliente);

                    if (res == true) {
                        Toast.makeText(FormClientes.this, "Cliente Alterado com Sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormClientes.this, "Erro; Nao foi possivel Alterar o Cliente.", Toast.LENGTH_SHORT).show();
                    }

                    db.close();
                }


            }
        });


    }
}
