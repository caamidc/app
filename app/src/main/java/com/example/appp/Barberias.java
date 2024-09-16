package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Barberias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberias);

        ListView listViewBarberias = findViewById(R.id.listviewbarberias);

        // Ejemplo de barberías
        final String[] barberias = {"Barbería 1", "Barbería 2", "Barbería 3"};
        listViewBarberias.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, barberias));

        // Configurar el listener para la selección de elementos
        listViewBarberias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el nombre de la barbería seleccionada
                String nombreBarberiaSeleccionada = barberias[position];

                // Crear un Intent para iniciar la actividad DetalleServicio
                Intent intent = new Intent(Barberias.this, DetalleSalon.class);

                // Proporcionar los datos reales para la barbería seleccionada
                intent.putExtra("nombre", nombreBarberiaSeleccionada);
                intent.putExtra("ubicacion", "Ubicación de " + nombreBarberiaSeleccionada);
                intent.putExtra("telefono", "Número de Teléfono de " + nombreBarberiaSeleccionada);
                intent.putExtra("horario", "Horario de " + nombreBarberiaSeleccionada);

                // Ejemplo de servicios
                intent.putExtra("servicios", new String[]{"Corte de pelo - $10.000", "Degradado - $15.000"});

                // Iniciar la actividad DetalleServicioConCalendario
                startActivity(intent);
            }
        });
    }
}