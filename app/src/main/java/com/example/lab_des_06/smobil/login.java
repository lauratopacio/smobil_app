package com.example.lab_des_06.smobil;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    EditText usuario, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);//borrar la barra de arriba con el nombre del proyecto
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.usu);
        password = (EditText) findViewById(R.id.pas);
        Button acceder = (Button) findViewById(R.id.acc);

        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usuario.getText().toString().isEmpty())
                {
                    usuario.setError("Ingrese su usuario");
                }
                if(password.getText().toString().isEmpty())
                {
                    password.setError("Ingrese su contrseña");
                }
                else{
                    ///mandar a la otra pantalla
                   // Intent Inicio = new Intent(MainActivity.this, Main2Activity.class);
                    //startActivity(Inicio);
//323 130 56 08
                }


            }
        });
    }
}