package com.example.appp;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

        listViewBarberias = findViewById(R.id.listviewbarberias);

        // Configurar el ListView con los datos de barberías
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, barberias);
        listViewBarberias.setAdapter(adapter);

        listViewBarberias.setOnItemClickListener((parent, view, position, id) -> {
            // Obtener el nombre de la barbería seleccionada
            String nombreBarberiaSeleccionada = barberias[position];

            // Crear una instancia del fragmento DetalleSalonFragment
            DetalleSalonFragment detalleSalonFragment = DetalleSalonFragment.newInstance(
                    nombreBarberiaSeleccionada,
                    "Ubicación: " + ubicacionBarberia,
                    "Número de Teléfono: " + telefonoBarberia,
                    "Horario: " + horarioBarberia,
                    new String[]{"Corte de pelo - $10.000", "Degradado - $15.000"}
            );

            // Cargar el fragmento en la vista
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detalleSalonFragment) // Asume que tienes un contenedor para los fragmentos
                    .addToBackStack(null) // Permitir volver al fragmento anterior
                    .commit();
        });
    }
}
