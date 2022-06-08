package com.example.redsocialuca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;

public class registro extends AppCompatActivity {
    EditText Correo, Nombre, Contraseña,Edad, Apellidos;
    Button RegistrarUsuario;
    
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar !=null;
        actionBar.setTitle("Registro");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        Correo = findViewById(R.id.correo);
        Nombre = findViewById(R.id.nombre);
        Contraseña = findViewById(R.id.contraseña);
        Edad = findViewById(R.id.Edad);
        Apellidos = findViewById(R.id.apellido);
        RegistrarUsuario = findViewById(R.id.Finalizar);
        
        firebaseAuth = firebaseAuth.getInstance();
        RegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = Correo.getText().toString();
                String pass = Contraseña.getText().toString();
                
                if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    Correo.setError("Correo no valido");
                    Correo.setFocusable(true);
                
                }else if (pass.length()>6){
                    Contraseña.setError("Contraseña no valida debe contener al menos 6 caracteres");
                    Contraseña.setFocusable(true);
                }else{
                    Registrar(correo,pass);
                }
            }
        });
    }

    private void Registrar(String correo, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(correo, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            assert user != null;
                            String uid = user.getUid();
                            String correo = Correo.getText().toString();
                            String pass = Contraseña.getText().toString();
                            String nombre = Nombre.getText().toString();
                            String apellido = Apellidos.getText().toString();
                            String edad = Edad.getText().toString();

                            HashMap<Object, String> DatosUsuario = new HashMap<>();

                            DatosUsuario.put("uid", uid);
                            DatosUsuario.put("correo", correo);
                            DatosUsuario.put("nombre", nombre);
                            DatosUsuario.put("pass", pass);
                            DatosUsuario.put("apellido", apellido);
                            DatosUsuario.put("edad", edad);

                            DatosUsuario.put("imagen", "");

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("USUARIOS_DE_APP");
                            reference.child(uid).setValue(DatosUsuario);
                            Toast.makeText(registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(registro.this,Inicio.class));

                        }else{
                            Toast.makeText(registro.this, "Algo slio mal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registro.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
