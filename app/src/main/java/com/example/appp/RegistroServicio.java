package com.example.appp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistroServicio extends AppCompatActivity {

    private EditText etNombreEmpresa, etUbicacion, etHorario, etServicios;
    private Spinner spinnerCategoria;
    private Button btnGuardar;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_servicio);

        // Inicializar los campos usando los nuevos IDs
        etNombreEmpresa = findViewById(R.id.editTextEmpresaNombre);
        etUbicacion = findViewById(R.id.editTextUbicacion);
        etHorario = findViewById(R.id.editTextHorario);
        etServicios = findViewById(R.id.editTextServicios);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnGuardar = findViewById(R.id.buttonGuardarEmpresa);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Configurar el spinner de categorías
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> guardarEmpresa());

        // Configurar BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(this::handleBottomNavigation);
    }
    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            startActivity(new Intent(this, InicioPrestador.class));
            return true;
        } else if (item.getItemId() == R.id.bottom_perfil) {
            startActivity(new Intent(this, Perfil.class));
            return true;
        }
        return false;
    }

    private void guardarEmpresa() {
        String nombre = etNombreEmpresa.getText().toString();
        String ubicacion = etUbicacion.getText().toString();
        String horario = etHorario.getText().toString();
        String servicios = etServicios.getText().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();

        // Crear un objeto Empresa con los datos
        Empresa empresa = new Empresa(nombre, ubicacion, horario, servicios, categoria);


        String prestadorId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        // Registrar la empresa en la subcolección "Empresas" de la colección "Prestadores"
        db.collection("prestadores").document(prestadorId)
                .collection("Empresas").add(empresa)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Empresa registrada con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroServicio.this, InicioPrestador.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al registrar la empresa: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}


