package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Salones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salones);

        ListView listViewSalones = findViewById(R.id.listViewSalones);

        // Ejemplo de datos de salones
        final String[] salones = {"Salón 1", "Salón 2", "Salón 3"};
        listViewSalones.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, salones));

        listViewSalones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Aquí obtienes el nombre del salón seleccionado
                String nombreSalonSeleccionado = salones[position];

                // Crear un Intent para iniciar la actividad DetalleSalon
                Intent intent = new Intent(Salones.this, DetalleSalon.class);

                // Aquí debes proporcionar los datos reales para cada salón
                intent.putExtra("nombre", nombreSalonSeleccionado);
                intent.putExtra("ubicacion", "Ubicación del " + nombreSalonSeleccionado);
                intent.putExtra("telefono", "Número de Teléfono del " + nombreSalonSeleccionado);
                intent.putExtra("horario", "Horario del " + nombreSalonSeleccionado);

                // Ejemplo de servicios, reemplaza con datos reales
                intent.putExtra("servicios", new String[]{"Servicio 1 - $10.000", "Servicio 2 - $20.000"});

                startActivity(intent);
            }
        });
    }
}

