package com.example.redsocialuca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_activity extends AppCompatActivity {

    Button LoginBTN, RegistarBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        LoginBTN = findViewById(R.id.LoginBTN);
        RegistarBTN = findViewById(R.id.RegistrarBTN);

        LoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_activity.this,Iniciar_sesion.class));
            }
        });

        RegistarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_activity.this,registro.class));

            }
        });
    }
}