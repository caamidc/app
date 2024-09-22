package com.example.appp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroServicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_servicio);

        EditText nombreEmpresaField = findViewById(R.id.editTextEmpresaNombre);
        EditText ubicacionField = findViewById(R.id.editTextUbicacion);
        EditText horarioField = findViewById(R.id.editTextHorario);
        EditText serviciosField = findViewById(R.id.editTextServicios);
        Button buttonGuardarEmpresa = findViewById(R.id.buttonGuardarEmpresa);

        buttonGuardarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores ingresados
                String nombreEmpresa = nombreEmpresaField.getText().toString().trim();
                String ubicacion = ubicacionField.getText().toString().trim();
                String horario = horarioField.getText().toString().trim();
                String servicios = serviciosField.getText().toString().trim();

                // Guardar datos en SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("Servicios", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nombre_empresa", nombreEmpresa);
                editor.putString("ubicacion", ubicacion);
                editor.putString("horario", horario);
                editor.putString("servicios", servicios);
                editor.apply();

                // Crear un Intent para abrir la actividad InicioPrestador
                Intent intent = new Intent(RegistroServicio.this, InicioPrestador.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

