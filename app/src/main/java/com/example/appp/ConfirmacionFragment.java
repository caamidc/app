package com.example.appp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConfirmacionFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_UBICACION = "ubicacion";
    private static final String ARG_TELEFONO = "telefono";
    private static final String ARG_HORARIO = "horario";

    private String nombre;
    private String ubicacion;
    private String telefono;
    private String horario;

    // Constructor vacío requerido
    public ConfirmacionFragment() {}

    // Método newInstance para crear una instancia del fragmento y pasar los datos
    public static ConfirmacionFragment newInstance(String nombre, String ubicacion, String telefono, String horario) {
        ConfirmacionFragment fragment = new ConfirmacionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_UBICACION, ubicacion);
        args.putString(ARG_TELEFONO, telefono);
        args.putString(ARG_HORARIO, horario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            ubicacion = getArguments().getString(ARG_UBICACION);
            telefono = getArguments().getString(ARG_TELEFONO);
            horario = getArguments().getString(ARG_HORARIO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_confirmacion, container, false);

        // Referencias a los TextViews
        TextView textViewNombre = view.findViewById(R.id.textViewNombre);
        TextView textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        TextView textViewTelefono = view.findViewById(R.id.textViewTelefono);
        TextView textViewHorario = view.findViewById(R.id.textViewHorario);

        // Establecer los textos
        textViewNombre.setText(nombre);
        textViewUbicacion.setText(ubicacion);
        textViewTelefono.setText(telefono);
        textViewHorario.setText(horario);

        // Botón de confirmación
        Button buttonConfirmar = view.findViewById(R.id.buttonConfirmar);
        buttonConfirmar.setOnClickListener(v -> {
            // Mostrar mensaje de éxito
            Toast.makeText(getContext(), "Reserva confirmada exitosamente", Toast.LENGTH_LONG).show();

            // Redirigir a la pantalla de inicio o donde sea necesario
            // Aquí puedes añadir la lógica para redirigir a otra pantalla si es necesario
        });

        return view;
    }
}

