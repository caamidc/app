package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Barberias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberias);

        ListView listViewBarberias = findViewById(R.id.listviewbarberias);

        // Ejemplo de barberías
        final String[] barberias = {"CorteFresh", "LaBarber", "TopBarber"};

        // Datos de ejemplo para cualquier barbería
        final String ubicacionBarberia = "Calle falsa 123";
        final String telefonoBarberia = "912345678";
        final String horarioBarberia = "Lun a Vie: 09:00am - 20:00pm";

        listViewBarberias.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, barberias));

        // Configurar el listener para la selección de elementos
        listViewBarberias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el nombre de la barbería seleccionada
                String nombreBarberiaSeleccionada = barberias[position];

                // Crear un Intent para iniciar la actividad DetalleServicio
                Intent intent = new Intent(Barberias.this, DetalleSalon.class);

                // Proporcionar los datos de ejemplo para la barbería seleccionada
                intent.putExtra("nombre", nombreBarberiaSeleccionada);
                intent.putExtra("ubicacion", "Ubicación: " + ubicacionBarberia);
                intent.putExtra("telefono", "Número de Teléfono: " + telefonoBarberia);
                intent.putExtra("horario", "Horario: " + horarioBarberia);

                // Ejemplo de servicios
                intent.putExtra("servicios", new String[]{"Corte de pelo - $10.000", "Degradado - $15.000"});

                // Iniciar la actividad
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                Intent intentInicio = new Intent(Barberias.this, Inicio.class);
                startActivity(intentInicio);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_perfil) {
                Intent intentPerfil = new Intent(Barberias.this, Perfil.class);
                startActivity(intentPerfil);
                finish();
                return true;
            }
            return false;
        });
    }
}

