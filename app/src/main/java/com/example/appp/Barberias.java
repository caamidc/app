package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Barberias extends AppCompatActivity {

    private ListView listViewBarberias;
    private final String[] barberias = {"Barbería A", "Barbería B", "Barbería C"};
    private final String ubicacionBarberia = "Ejemplo de ubicación";
    private final String telefonoBarberia = "123456789";
    private final String horarioBarberia = "Lunes a Viernes: 9:00 - 19:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberias);

        // Configuración del ListView
        listViewBarberias = findViewById(R.id.listviewbarberias);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, barberias);
        listViewBarberias.setAdapter(adapter);

        // Manejo de la selección en el ListView
        listViewBarberias.setOnItemClickListener((parent, view, position, id) -> {
            String nombreBarberiaSeleccionada = barberias[position];
            DetalleSalonFragment detalleSalonFragment = DetalleSalonFragment.newInstance(
                    nombreBarberiaSeleccionada,
                    "Ubicación: " + ubicacionBarberia,
                    "Número de Teléfono: " + telefonoBarberia,
                    "Horario: " + horarioBarberia,
                    new String[]{"Corte de pelo - $10.000", "Degradado - $15.000"}
            );

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detalleSalonFragment)
                    .addToBackStack(null)
                    .commit();
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this::handleBottomNavigation);
    }

    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            startActivity(new Intent(this, InicioUsuario.class));
            return true;
        } else if (item.getItemId() == R.id.bottom_perfil) {
            startActivity(new Intent(this, Perfil.class));
            return true;
        }
        return false;
    }
}
