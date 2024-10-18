package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClinicasMedicas extends AppCompatActivity {

    private ListView listViewClinicas;
    private final String[] clinicas = {"Clínica Atacama", "Salud", "RedSalud"};
    private final String ubicacionClinica = "Calle Falsa 321";
    private final String telefonoClinica = "912345678";
    private final String horarioClinica = "Lun a Vie: 08:00am - 18:00pm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicas_medicas);

        // Configuración del ListView
        listViewClinicas = findViewById(R.id.listviewclinicas);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clinicas);
        listViewClinicas.setAdapter(adapter);

        // Manejo de la selección en el ListView
        listViewClinicas.setOnItemClickListener((parent, view, position, id) -> {
            String nombreClinicaSeleccionada = clinicas[position];
            DetalleSalonFragment detalleSalonFragment = DetalleSalonFragment.newInstance(
                    nombreClinicaSeleccionada,
                    "Ubicación: " + ubicacionClinica,
                    "Número de Teléfono: " + telefonoClinica,
                    "Horario: " + horarioClinica,
                    new String[]{"Consulta general - $10.000", "Medicina infantil - $25.000"}
            );

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detalleSalonFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Implementación del BottomNavigationView
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

