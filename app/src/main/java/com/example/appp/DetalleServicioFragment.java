package com.example.appp;

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
import androidx.fragment.app.FragmentManager;

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

        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            ubicacion = getArguments().getString(ARG_UBICACION);
            telefono = getArguments().getString(ARG_TELEFONO);
            servicio = getArguments().getString(ARG_SERVICIO);
        }

        TextView textViewNombreSalon = view.findViewById(R.id.textViewNombreSalon);
        TextView textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        TextView textViewTelefono = view.findViewById(R.id.textViewTelefono);
        TextView textViewHorario = view.findViewById(R.id.textViewHorario);
        TextView textViewNombreServicio = view.findViewById(R.id.textViewNombreServicio);
        TextView textViewPrecio = view.findViewById(R.id.textViewPrecio);
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        listViewHorarios = view.findViewById(R.id.listViewHorarios);

        textViewNombreSalon.setText(nombre);
        textViewUbicacion.setText(ubicacion);
        textViewTelefono.setText(telefono);
        textViewNombreServicio.setText(servicio);

        horariosList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, horariosList);
        listViewHorarios.setAdapter(adapter);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
            cargarHorariosDisponibles(fechaSeleccionada);
        });

        listViewHorarios.setOnItemClickListener((parent, view1, position, id) -> {
            horarioSeleccionado = horariosList.get(position); // Guardar el horario seleccionado

            // Navegar a ConfirmacionFragment
            FragmentManager fragmentManager = getParentFragmentManager();
            ConfirmacionFragment confirmacionFragment = ConfirmacionFragment.newInstance(nombre, ubicacion, telefono, horarioSeleccionado);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, confirmacionFragment) // Asegúrate de usar el ID correcto del contenedor
                    .addToBackStack(null) // Para poder volver al fragmento anterior
                    .commit();
        });

        return view;
    }

    private void cargarHorariosDisponibles(String fecha) {
        horariosList.clear();
        horariosList.add("09:00 AM");
        horariosList.add("10:00 AM");
        horariosList.add("11:00 AM");
        horariosList.add("12:00 PM");
        horariosList.add("01:00 PM");

        adapter.notifyDataSetChanged();
    }
}

