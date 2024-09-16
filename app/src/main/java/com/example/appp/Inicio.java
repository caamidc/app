package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Encontrar el botón "Salones de belleza" por su ID
        Button salonesButton = findViewById(R.id.button3);

        // Encontrar el botón "Barberías" por su ID
        Button barberiasButton = findViewById(R.id.button4);

        // Encontrar el botón "Clínicas médicas" por su ID
        Button clinicasButton = findViewById(R.id.button5);

        // Encontrar el botón "Clínicas médicas" por su ID
        Button serviciosButton = findViewById(R.id.button6);

        // Configurar el listener para manejar el clic en el botón
        salonesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la actividad Salones
                Intent intent = new Intent(Inicio.this, Salones.class);
                startActivity(intent);
            }
        });

        // Configurar el listener para manejar el clic en el botón "Barberías"
        barberiasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la actividad Barberias
                Intent intent = new Intent(Inicio.this, Barberias.class);
                startActivity(intent);
            }
        });

        // Configurar el listener para manejar el clic en el botón "Clínicas médicas"
        clinicasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la actividad Clinicas
                Intent intent = new Intent(Inicio.this, ClinicasMedicas.class);
                startActivity(intent);
            }
        });

        // Configurar el listener para manejar el clic en el botón "servicio mascotas"
        serviciosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la actividad Servicios
                Intent intent = new Intent(Inicio.this, ServicioMascotas.class);
                startActivity(intent);
            }
        });
    }
}