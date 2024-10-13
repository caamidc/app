package com.example.appp;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Salones extends AppCompatActivity {

    private ListView listViewSalones;
    private final String[] salones = {"Belleza Única", "Flowers", "Top Belleza"};
    private final String ubicacionSalon = "Copayapu 777";
    private final String telefonoSalon = "912345678";
    private final String horarioSalon = "Lun a Vie: 11:00am - 19:00pm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salones);

        listViewSalones = findViewById(R.id.listViewSalones);

        // Configurar el ListView con los datos de salones
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, salones);
        listViewSalones.setAdapter(adapter);

        listViewSalones.setOnItemClickListener((parent, view, position, id) -> {
            // Obtener el nombre del salón seleccionado
            String nombreSalonSeleccionado = salones[position];

            // Crear una instancia del fragmento DetalleSalonFragment
            DetalleSalonFragment detalleSalonFragment = DetalleSalonFragment.newInstance(
                    nombreSalonSeleccionado,
                    "Ubicación: " + ubicacionSalon,
                    "Número de Teléfono: " + telefonoSalon,
                    "Horario: " + horarioSalon,
                    new String[]{"Maquillaje - $10.000", "Manicure y Pedicure - $35.000"}
            );

            // Cargar el fragmento en la vista
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detalleSalonFragment) // Asume que tienes un contenedor para los fragmentos
                    .addToBackStack(null) // Permitir volver al fragmento anterior
                    .commit();
        });
    }
}
