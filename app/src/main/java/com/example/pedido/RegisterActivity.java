package com.example.pedido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText et_name, et_user, et_passwd, et_cpasswd;
    Button bt_new_user;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Cadastro de Usuarios");

        db = new Database(this);

        et_name = (EditText) findViewById(R.id.et_reg_name);
        et_user = (EditText) findViewById(R.id.et_reg_user);
        et_passwd = (EditText) findViewById(R.id.et_reg_passwd);
        et_cpasswd = (EditText) findViewById(R.id.et_reg_cpasswd);

        bt_new_user = (Button) findViewById(R.id.bt_reg_new);

        bt_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = et_name.getText().toString();
                String suser = et_user.getText().toString();
                String spasswd = et_passwd.getText().toString();
                String scpasswd = et_cpasswd.getText().toString();


                if (suser.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Informe o Usuario.", Toast.LENGTH_SHORT).show();
                } else if (sname.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Informe o Nome do usuario.", Toast.LENGTH_SHORT).show();
                } else if (spasswd.equals("") || scpasswd.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Informe a Senha.", Toast.LENGTH_SHORT).show();
                } else if (!spasswd.equals(scpasswd)) {
                    Toast.makeText(RegisterActivity.this, "As duas senhas nao correspondem.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean chkUser = db.chkUser(suser);
                    if (chkUser == true) {

                        Boolean res = db.insertUser(sname, suser, spasswd);

                        if (res == true) {
                            Toast.makeText(RegisterActivity.this, "Usuario inserido com Sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Erro; Nao foi possivel inserir o usuario.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Usuario ja cadastrado.", Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });

    }
}
