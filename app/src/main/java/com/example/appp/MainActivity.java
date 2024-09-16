package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Encontrar los campos y botones por su ID
        EditText emailField = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordField = findViewById(R.id.editTextTextPassword);
        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores ingresados
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                // Validar las credenciales y redirigir a la pantalla correspondiente
                if (email.equals("cami@gmail.com") && password.equals("123")) {
                    Intent intent = new Intent(MainActivity.this, Inicio.class);
                    startActivity(intent);
                } else if (email.equals("camila@gmail.com") && password.equals("321")) {
                    Intent intent = new Intent(MainActivity.this, InicioPrestador.class);
                    startActivity(intent);
                } else {
                    // Mostrar un mensaje de error si las credenciales son incorrectas
                    // Puedes agregar un Toast o una alerta aquí
                }
            }
        });

        // Configurar el botón de registro
        Button registerButton = findViewById(R.id.button2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });
    }
}
