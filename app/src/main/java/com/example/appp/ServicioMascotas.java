package com.example.appp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ServicioMascotas extends AppCompatActivity {

    private ListView listViewMascotas;
    private final String[] serviciosMascotas = {"Antuki", "CatDog", "Animalistas"};
    private final String ubicacionMascotas = "Circunvalación 666";
    private final String telefonoMascotas = "912345678";
    private final String horarioMascotas = "Lun a Vie: 09:00am - 21:00pm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_mascotas);

        listViewMascotas = findViewById(R.id.listviewmascotas);

        // Configurar el ListView con los datos de servicios de mascotas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviciosMascotas);
        listViewMascotas.setAdapter(adapter);

        listViewMascotas.setOnItemClickListener((parent, view, position, id) -> {
            // Obtener el nombre del servicio de mascotas seleccionado
            String servicioSeleccionado = serviciosMascotas[position];

            // Crear una instancia del fragmento DetalleSalonFragment
            DetalleSalonFragment detalleSalonFragment = DetalleSalonFragment.newInstance(
                    servicioSeleccionado,
                    "Ubicación: " + ubicacionMascotas,
                    "Número de Teléfono: " + telefonoMascotas,
                    "Horario: " + horarioMascotas,
                    new String[]{"Peluquería canina - $35.000", "Consulta veterinaria - $20.000"}
            );

            // Cargar el fragmento en la vista
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detalleSalonFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}

