package com.example.appp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

        listViewClinicas = findViewById(R.id.listviewclinicas);

        // Configurar el ListView con los datos de clínicas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clinicas);
        listViewClinicas.setAdapter(adapter);

        listViewClinicas.setOnItemClickListener((parent, view, position, id) -> {
            // Obtener el nombre de la clínica seleccionada
            String nombreClinicaSeleccionada = clinicas[position];

            // Crear una instancia del fragmento DetalleSalonFragment
            DetalleSalonFragment detalleSalonFragment = DetalleSalonFragment.newInstance(
                    nombreClinicaSeleccionada,
                    "Ubicación: " + ubicacionClinica,
                    "Número de Teléfono: " + telefonoClinica,
                    "Horario: " + horarioClinica,
                    new String[]{"Consulta general - $10.000", "Medicina infantil - $25.000"}
            );

            // Cargar el fragmento en la vista
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detalleSalonFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}

