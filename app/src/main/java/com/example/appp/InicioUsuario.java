package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuario);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(this::handleBottomNavigation);

        findViewById(R.id.button3).setOnClickListener(v -> startActivity(new Intent(this, Salones.class)));
        findViewById(R.id.button4).setOnClickListener(v -> startActivity(new Intent(this, Barberias.class)));
        findViewById(R.id.button5).setOnClickListener(v -> startActivity(new Intent(this, ClinicasMedicas.class)));
        findViewById(R.id.button6).setOnClickListener(v -> startActivity(new Intent(this, ServicioMascotas.class)));
    }

    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            return true; // No hacer nada si se selecciona "Inicio"
        } else if (item.getItemId() == R.id.bottom_perfil) {
            Intent intent = new Intent(this, Perfil.class);
            intent.putExtra("fromActivity", "InicioUsuario"); // Indicar que viene de InicioUsuario
            startActivity(intent);
            finish(); // Cerrar esta actividad si se navega al perfil
            return true;
        }
        return false;
    }

}

