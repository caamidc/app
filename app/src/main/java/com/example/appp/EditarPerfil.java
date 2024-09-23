package com.example.appp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditarPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                Intent intentInicio = new Intent(EditarPerfil.this, Inicio.class);
                startActivity(intentInicio);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_perfil) {
                Intent intentPerfil = new Intent(EditarPerfil.this, Perfil.class);
                startActivity(intentPerfil);
                finish();
                return true;
            }
            return false;
        });
    }
}
