package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroServicio extends AppCompatActivity {

    private EditText etNombreEmpresa, etUbicacion, etHorarioApertura, etHorarioCierre, etServicios, etTelefono;
    private Spinner spinnerCategoria;
    private Button btnGuardar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_servicio);

        // Inicializar los campos
        etNombreEmpresa = findViewById(R.id.editTextEmpresaNombre);
        etUbicacion = findViewById(R.id.editTextUbicacion);
        etTelefono = findViewById(R.id.editTextTelefono);  // Campo de teléfono
        etHorarioApertura = findViewById(R.id.editTextHorarioApertura); // Campo de horario de apertura
        etHorarioCierre = findViewById(R.id.editTextHorarioCierre); // Campo de horario de cierre
        etServicios = findViewById(R.id.editTextServicios); // Campo para servicios disponibles
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
        String nombre = etNombreEmpresa.getText().toString().trim();  // Nombre de la empresa
        String ubicacion = etUbicacion.getText().toString().trim();  // Ubicación
        String telefono = etTelefono.getText().toString().trim();    // Teléfono
        String horarioApertura = etHorarioApertura.getText().toString().trim(); // Horario de apertura
        String horarioCierre = etHorarioCierre.getText().toString().trim(); // Horario de cierre
        String servicios = etServicios.getText().toString().trim(); // Servicios
        String categoria = spinnerCategoria.getSelectedItem().toString(); // Categoría

        // Crear un objeto Empresa con los datos
        Empresa empresa = new Empresa(nombre, ubicacion, telefono, horarioApertura, horarioCierre, servicios, categoria);

        // Registrar la empresa en la subcolección "Empresa" de la colección "prestadores"
        db.collection("prestadores").document("empresa")
                .collection("Empresa").add(empresa)
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

