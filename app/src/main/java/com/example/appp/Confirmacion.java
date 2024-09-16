package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

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

        // Obtener el botón de confirmación
        Button buttonConfirmar = findViewById(R.id.buttonConfirmar);

        // Configurar el listener para el botón
        buttonConfirmar.setOnClickListener(v -> {
            // Mostrar un mensaje de éxito
            Toast.makeText(Confirmacion.this, "Reserva confirmada exitosamente", Toast.LENGTH_LONG).show();

            // Redirigir al usuario a la pantalla de inicio
            Intent intent = new Intent(Confirmacion.this, Inicio.class);
            startActivity(intent);

            // Opcional: Cerrar la actividad actual
            finish();
        });
    }
}
