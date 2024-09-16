package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroServicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_servicio);
        // Encontrar el botón por su ID
        Button buttonGuardarEmpresa = findViewById(R.id.buttonGuardarEmpresa);

        // Configurar el listener para manejar el clic en el botón
        buttonGuardarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la actividad InicioPrestador
                Intent intent = new Intent(RegistroServicio.this, InicioPrestador.class);
                // Iniciar la actividad InicioPrestador
                startActivity(intent);
                // Finalizar la actividad actual
                finish();
            }
        });
    }
}