package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DetalleServicio extends AppCompatActivity {

    private ListView listViewHorarios;
    private ArrayList<String> horariosList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_servicio);

        // Obtener los datos de la Intent
        String nombre = getIntent().getStringExtra("nombre");
        String ubicacion = getIntent().getStringExtra("ubicacion");
        String telefono = getIntent().getStringExtra("telefono");
        String horario = getIntent().getStringExtra("horario");

        // Referencias a los TextViews
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewUbicacion = findViewById(R.id.textViewUbicacion);
        TextView textViewTelefono = findViewById(R.id.textViewTelefono);
        TextView textViewHorario = findViewById(R.id.textViewHorario);

        // Establecer los textos
        textViewNombre.setText(nombre);
        textViewUbicacion.setText(ubicacion);
        textViewTelefono.setText(telefono);
        textViewHorario.setText(horario);

        // Configurar el calendario
        CalendarView calendarView = findViewById(R.id.calendarView);
        listViewHorarios = findViewById(R.id.listViewHorarios);
        horariosList = new ArrayList<>();

        // Configurar el adaptador una sola vez
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, horariosList);
        listViewHorarios.setAdapter(adapter);

        // Listener para cuando se selecciona una fecha
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(DetalleServicio.this, "Fecha seleccionada: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();

            // Generar horarios para la fecha seleccionada
            cargarHorarios(fechaSeleccionada);
        });

        // Listener para cuando se selecciona un horario
        listViewHorarios.setOnItemClickListener((parent, view, position, id) -> {
            // Obtener el horario seleccionado
            String horarioSeleccionado = horariosList.get(position);

            // Crear un Intent para ir a la actividad de Confirmación
            Intent intent = new Intent(DetalleServicio.this, Confirmacion.class);

            // Pasar datos necesarios a Confirmacion.java
            intent.putExtra("nombre", nombre);
            intent.putExtra("ubicacion", ubicacion);
            intent.putExtra("telefono", telefono);
            intent.putExtra("horario", horarioSeleccionado);

            // Iniciar la actividad de Confirmación
            startActivity(intent);
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                Intent intentInicio = new Intent(DetalleServicio.this, Inicio.class);
                startActivity(intentInicio);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_perfil) {
                Intent intentPerfil = new Intent(DetalleServicio.this, Perfil.class);
                startActivity(intentPerfil);
                finish();
                return true;
            }
            return false;
        });
    }

    private void cargarHorarios(String fecha) {
        // Limpiar la lista de horarios y agregar nuevos horarios para la fecha seleccionada
        horariosList.clear();
        horariosList.add(fecha + " - 10:00 AM");
        horariosList.add(fecha + " - 12:00 PM");
        horariosList.add(fecha + " - 2:00 PM");
        horariosList.add(fecha + " - 4:00 PM");

        // Notificar al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged();
    }

    }