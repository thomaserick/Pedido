package com.example.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText et_user,et_passwd;
    Button btRegister,btLogin;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        btLogin=(Button)findViewById(R.id.bt_login);
        btRegister=(Button)findViewById(R.id.bt_register);

        et_user = (EditText)findViewById(R.id.et_user);
        et_passwd =(EditText)findViewById(R.id.et_passw);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String suser = et_user.getText().toString();
                String spasswd = et_passwd.getText().toString();

                if (suser.equals("")){
                    Toast.makeText(MainActivity.this,"Informe o Usuario",Toast.LENGTH_SHORT).show();
                }else if(spasswd.equals("")){
                    Toast.makeText(MainActivity.this,"Informe a Senha",Toast.LENGTH_SHORT).show();
                }else {

                    String res = db.validarLogin(suser,spasswd);
                    if(res.equals("OK")){
                        Toast.makeText(MainActivity.this,"Logado com Sucesso",Toast.LENGTH_SHORT).show();
                        Intent i  = new Intent(MainActivity.this, DashbordActivity.class);
                        startActivity(i);


                    }else{
                        Toast.makeText(MainActivity.this,"Usuario ou Senha invalido(s)",Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });


        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });


    }
}
