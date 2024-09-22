package com.example.appp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InicioPrestador extends AppCompatActivity {

    private ArrayList<String> serviciosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_prestador);

        ListView listViewServicios = findViewById(R.id.listViewServicios);
        serviciosList = new ArrayList<>();

        // Obtener datos desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Servicios", MODE_PRIVATE);
        String nombreEmpresa = sharedPreferences.getString("nombre_empresa", null);
        String ubicacion = sharedPreferences.getString("ubicacion", null);
        String horario = sharedPreferences.getString("horario", null);
        String servicios = sharedPreferences.getString("servicios", null);

        // Crear un string con todos los datos
        if (nombreEmpresa != null) {
            String infoServicio = "Empresa: " + nombreEmpresa + "\n" +
                    "Ubicación: " + ubicacion + "\n" +
                    "Horario: " + horario + "\n" +
                    "Servicios: " + servicios;

            // Agregar el servicio a la lista
            serviciosList.add(infoServicio);

            // Mostrar en el ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviciosList);
            listViewServicios.setAdapter(adapter);
        }

        FloatingActionButton fabAddEmpresa = findViewById(R.id.fab_add_empresa);
        fabAddEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicioPrestador.this, RegistroServicio.class);
                startActivity(intent);
            }
        });
    }
}
