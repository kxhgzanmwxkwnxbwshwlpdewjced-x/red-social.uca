package com.example.redsocialuca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class primera_pantalla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primera_pantalla);

        final int Duracion=2500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(primera_pantalla.this, main_activity.class);
                startActivity(intent);

            }
        },Duracion);
    }
}