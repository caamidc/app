package com.example.appp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Login extends AppCompatActivity {


    // Variables para Firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText etEmail, etPassword;
    public Button btnLogin, btnRegister;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar Firebase Auth y Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Enlazar vistas con las IDs del XML
        etEmail= findViewById(R.id.editTextTextEmailAddress);
        etPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.button);
        btnRegister = findViewById(R.id.button2);

        btnLogin.setOnClickListener(v -> loginUser());

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Registro.class);
            startActivity(intent);
        });
    }

    private void loginUser(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if(currentUser !=null){
                            checkUserType(currentUser.getUid());
                        }
                    } else {
                        Toast.makeText(this, "Error al iniciar sesión: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUserType(String userId){
        // Verificar el tipo de usuario en Firestore
        db.collection("usuarios").document(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Redirigir a la actividad de usuario
                            Intent intent = new Intent(Login.this, InicioUsuario.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Comprobar si es un prestador de servicios
                            db.collection("prestadores").document(userId).get()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful() && task1.getResult() != null && task1.getResult().exists()) {
                                            // Redirigir a la actividad de prestador de servicios
                                            Intent intent = new Intent(Login.this, InicioPrestador.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(this, "Error al verificar el tipo de usuario: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}


