package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ClinicasMedicas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicas_medicas);

        ListView listViewClinicas = findViewById(R.id.listviewclinicas);

        // Crear una lista de ejemplo
        final String[] clinicas = {"Clinica Atacama", "Salud", "RedSalud"};
        listViewClinicas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clinicas));

        // Agregar el Listener para manejar los clics en la lista
        listViewClinicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el nombre de la clínica seleccionada
                String nombreClinicaSeleccionada = clinicas[position];

                // Datos de ejemplo
                final String ubicacionClinica = "Calle falsa 321";
                final String telefonoClinica = "912345678";
                final String horarioClinica = "Lun a Vie: 08:00am - 18:00pm";

                // Crear un Intent para iniciar la actividad DetalleSalon (o DetalleClinica)
                Intent intent = new Intent(ClinicasMedicas.this, DetalleSalon.class);

                // Proporcionar los datos de la clínica seleccionada
                intent.putExtra("nombre", nombreClinicaSeleccionada);
                intent.putExtra("ubicacion", "Ubicación: " + ubicacionClinica);
                intent.putExtra("telefono", "Número de Teléfono: " + telefonoClinica);
                intent.putExtra("horario", "Horario: " + horarioClinica);

                // Ejemplo de servicios
                intent.putExtra("servicios", new String[]{"Consulta general - $10.000", "Medicina infantil - $25.000"});

                startActivity(intent);
            }
        });
    }
}
