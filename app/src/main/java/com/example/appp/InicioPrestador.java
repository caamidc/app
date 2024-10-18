package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InicioPrestador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_prestador);

        // Botón flotante para agregar servicios
        FloatingActionButton fab = findViewById(R.id.fab_add_empresa);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(InicioPrestador.this, RegistroServicio.class);
            startActivity(intent);
        });

        // Manejo del BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(this::handleBottomNavigation);
    }

    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            // Ya estamos en la pantalla principal
            return true;
        } else if (item.getItemId() == R.id.bottom_perfil) {
            // Ir a la pantalla de perfil
            startActivity(new Intent(this, Perfil.class));
            finish();
            return true;
        }
        return false;
    }
}

