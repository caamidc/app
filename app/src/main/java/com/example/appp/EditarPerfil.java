package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditarPerfil extends AppCompatActivity {

    private EditText editarnombre, editarcorreo, editarcontrasena;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        editarnombre = findViewById(R.id.editarnombre);
        editarcorreo = findViewById(R.id.editarcorreo);
        editarcontrasena = findViewById(R.id.editarcontrasena);
        btnGuardar = findViewById(R.id.btn_guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
            }
        });

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
    private void guardarCambios() {
        String nombre = editarnombre.getText().toString();
        String correo = editarcorreo.getText().toString();
        String contrasena = editarcontrasena.getText().toString();

        Toast.makeText(EditarPerfil.this, "Cambios guardados", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditarPerfil.this, Perfil.class);
        startActivity(intent);
    }
}
