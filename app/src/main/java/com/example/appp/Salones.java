package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Salones extends AppCompatActivity {

    public ListView listViewSalones;
    private FirebaseFirestore db;
    private final List<Empresa> listaEmpresas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salones);

        listViewSalones = findViewById(R.id.listViewSalones);
        db = FirebaseFirestore.getInstance();

        // Configurar BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home); // Por defecto, selecciona "Inicio"
        bottomNavigationView.setOnItemSelectedListener(this::handleBottomNavigation);

        // Cargar los salones desde Firestore
        cargarSalones();
    }

    private void cargarSalones() {
        db.collection("Empresas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        int count = 0;
                        listaEmpresas.clear();

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            count++;
                            Empresa empresa = document.toObject(Empresa.class);
                            listaEmpresas.add(empresa);
                        }

                        Toast.makeText(this, "Total de salones recuperados: " + count, Toast.LENGTH_SHORT).show();

                        EmpresaAdapter adapter = new EmpresaAdapter(this, listaEmpresas);
                        listViewSalones.setAdapter(adapter);

                        if (count == 0) {
                            Toast.makeText(this, "No se encontraron salones.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Error al cargar salones: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            // Si ya estamos en la pantalla de inicio, no hacer nada
            return true;
        } else if (item.getItemId() == R.id.bottom_perfil) {
            // Navegar a la pantalla de perfil
            startActivity(new Intent(this, Perfil.class));
            finish();
            return true;
        }
        return false;
    }
}
