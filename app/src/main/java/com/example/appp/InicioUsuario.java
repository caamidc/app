package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuario);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        // Menú de navegación
        bottomNavigationView.setOnItemSelectedListener(item -> {
            // Usando if en lugar de switch
            if (item.getItemId() == R.id.bottom_home) {
                return true; // Ya estamos en la actividad de inicio
            } else if (item.getItemId() == R.id.bottom_perfil) {
                Intent intentPerfil = new Intent(this, Perfil.class);
                startActivity(intentPerfil);
                finish(); // Termina la actividad actual si deseas que no se regrese
                return true;
            }
            return false;
        });

        // Encontrar los botones por su ID
        Button salonesButton = findViewById(R.id.button3);
        Button barberiasButton = findViewById(R.id.button4);
        Button clinicasButton = findViewById(R.id.button5);
        Button serviciosButton = findViewById(R.id.button6);

        // Configurar los listeners para los botones
        salonesButton.setOnClickListener(view -> {
            Intent intent = new Intent(InicioUsuario.this, Salones.class);
            startActivity(intent);
        });

        barberiasButton.setOnClickListener(view -> {
            Intent intent = new Intent(InicioUsuario.this, Barberias.class);
            startActivity(intent);
        });

        clinicasButton.setOnClickListener(view -> {
            Intent intent = new Intent(InicioUsuario.this, ClinicasMedicas.class);
            startActivity(intent);
        });

        serviciosButton.setOnClickListener(view -> {
            Intent intent = new Intent(InicioUsuario.this, ServicioMascotas.class);
            startActivity(intent);
        });
    }
}
