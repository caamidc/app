package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DetalleServicioFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_UBICACION = "ubicacion";
    private static final String ARG_TELEFONO = "telefono";
    private static final String ARG_SERVICIO = "servicio";

    private String nombre;
    private String ubicacion;
    private String telefono;
    private String servicio;
    private String horarioSeleccionado;

    private ArrayList<String> horariosList;
    private ArrayAdapter<String> adapter;
    private ListView listViewHorarios;

    // Método newInstance para crear instancias del fragmento con los argumentos necesarios
    public static DetalleServicioFragment newInstance(String nombre, String ubicacion, String telefono, String servicio) {
        DetalleServicioFragment fragment = new DetalleServicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_UBICACION, ubicacion);
        args.putString(ARG_TELEFONO, telefono);
        args.putString(ARG_SERVICIO, servicio);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detalle_servicio, container, false);

        // Obtener los argumentos
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            ubicacion = getArguments().getString(ARG_UBICACION);
            telefono = getArguments().getString(ARG_TELEFONO);
            servicio = getArguments().getString(ARG_SERVICIO);
        }

        // Referencias a los elementos de la interfaz
        TextView textViewNombreSalon = view.findViewById(R.id.textViewNombreSalon);
        TextView textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        TextView textViewTelefono = view.findViewById(R.id.textViewTelefono);
        TextView textViewHorario = view.findViewById(R.id.textViewHorario);
        TextView textViewNombreServicio = view.findViewById(R.id.textViewNombreServicio);
        TextView textViewPrecio = view.findViewById(R.id.textViewPrecio);
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        listViewHorarios = view.findViewById(R.id.listViewHorarios);
        Button buttonConfirmar = view.findViewById(R.id.buttonConfirmar);

        // Establecer los textos de los TextViews con los datos recibidos
        textViewNombreSalon.setText(nombre);
        textViewUbicacion.setText(ubicacion);
        textViewTelefono.setText(telefono);
        textViewHorario.setText(""); // Horario vacío hasta seleccionar uno
        textViewNombreServicio.setText(servicio);

        // Inicializar la lista de horarios
        horariosList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, horariosList);
        listViewHorarios.setAdapter(adapter);

        // Listener para seleccionar fecha en el calendario
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
            cargarHorariosDisponibles(fechaSeleccionada);
        });

        // Listener para el botón de confirmar
        buttonConfirmar.setOnClickListener(v -> {
            if (horarioSeleccionado != null) {
                // Mostrar mensaje de confirmación de reserva
                Toast.makeText(getContext(), "Reserva confirmada para " + servicio + " a las " + horarioSeleccionado, Toast.LENGTH_SHORT).show();

                // Esperar unos segundos para que el mensaje sea visible
                buttonConfirmar.postDelayed(() -> {
                    // Redirigir a la actividad InicioUsuario
                    Intent intent = new Intent(getActivity(), InicioUsuario.class);
                    startActivity(intent);
                }, 1500); // Espera 1.5 segundos para que el mensaje se vea

            } else {
                // Mostrar mensaje pidiendo seleccionar un horario
                Toast.makeText(getContext(), "Por favor, selecciona un horario", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener para seleccionar un horario
        listViewHorarios.setOnItemClickListener((parent, view1, position, id) -> {
            horarioSeleccionado = horariosList.get(position); // Guardar el horario seleccionado
            textViewHorario.setText(horarioSeleccionado); // Mostrar el horario seleccionado en el TextView
        });

        return view;
    }

    // Método para cargar horarios disponibles para una fecha seleccionada
    private void cargarHorariosDisponibles(String fecha) {
        // Aquí puedes cargar los horarios disponibles para la fecha seleccionada
        horariosList.clear();
        horariosList.add("09:00 AM");
        horariosList.add("10:00 AM");
        horariosList.add("11:00 AM");
        horariosList.add("12:00 PM");
        horariosList.add("01:00 PM");

        adapter.notifyDataSetChanged(); // Notificar al adaptador de cambios
    }
}
