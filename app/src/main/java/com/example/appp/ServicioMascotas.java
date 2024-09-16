package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ServicioMascotas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_mascotas);

        ListView listViewMascotas = findViewById(R.id.listViewMascotas);

        // Crear una lista de ejemplo
        final String[] serviciosMascotas = {"Mascotas 1", "Mascotas 2", "Mascotas 3"};

        // Configurar el adaptador para el ListView
        listViewMascotas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviciosMascotas));

        // Agregar el Listener para manejar los clics en la lista
        listViewMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el nombre del servicio seleccionado
                String servicioSeleccionado = serviciosMascotas[position];

                // Crear un Intent para iniciar la actividad DetalleSalon (o DetalleServicio)
                Intent intent = new Intent(ServicioMascotas.this, DetalleSalon.class);

                // Proporcionar los datos del servicio de mascotas seleccionado
                intent.putExtra("nombre", servicioSeleccionado);
                intent.putExtra("ubicacion", "Ubicación " + servicioSeleccionado);
                intent.putExtra("telefono", "Número de Teléfono " + servicioSeleccionado);
                intent.putExtra("horario", "Horario " + servicioSeleccionado);

                // Ejemplo de servicios
                intent.putExtra("servicios", new String[]{"Peluqueria canina - $35.000", "Consulta veterinaria - $20.000"});

                startActivity(intent);
            }
        });
    }
}
