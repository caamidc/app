package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleSalon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_salon);

        // Obtener datos de la intent
        String nombre = getIntent().getStringExtra("nombre");
        String ubicacion = getIntent().getStringExtra("ubicacion");
        String telefono = getIntent().getStringExtra("telefono");
        String horario = getIntent().getStringExtra("horario");
        String[] servicios = getIntent().getStringArrayExtra("servicios");

        // Referencias a los TextViews
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewUbicacion = findViewById(R.id.textViewUbicacion);
        TextView textViewTelefono = findViewById(R.id.textViewTelefono);
        TextView textViewHorario = findViewById(R.id.textViewHorario);
        ListView listViewServicios = findViewById(R.id.listViewServicios);

        // Establecer los textos
        textViewNombre.setText(nombre);
        textViewUbicacion.setText(ubicacion);
        textViewTelefono.setText(telefono);
        textViewHorario.setText(horario);

        // Configurar el ListView de servicios
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, servicios);
        listViewServicios.setAdapter(adapter);

        // Listener para seleccionar un servicio
        listViewServicios.setOnItemClickListener((parent, view, position, id) -> {
            String servicioSeleccionado = servicios[position];

            // Crear un Intent para iniciar la actividad DetalleServicio
            Intent intent = new Intent(DetalleSalon.this, DetalleServicio.class);

            // Pasar los datos del salón y el servicio seleccionado a la actividad DetalleServicio
            intent.putExtra("nombre", nombre);
            intent.putExtra("ubicacion", ubicacion);
            intent.putExtra("telefono", telefono);
            intent.putExtra("horario", horario);
            intent.putExtra("servicio", servicioSeleccionado);

            // Iniciar la actividad DetalleServicio
            startActivity(intent);
        });
    }
}

