package com.example.appp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetalleSalonFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_UBICACION = "ubicacion";
    private static final String ARG_TELEFONO = "telefono";
    private static final String ARG_HORARIO = "horario";
    private static final String ARG_SERVICIOS = "servicios";

    public static DetalleSalonFragment newInstance(String nombre, String ubicacion, String telefono, String horario, String[] servicios) {
        DetalleSalonFragment fragment = new DetalleSalonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_UBICACION, ubicacion);
        args.putString(ARG_TELEFONO, telefono);
        args.putString(ARG_HORARIO, horario);
        args.putStringArray(ARG_SERVICIOS, servicios);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detalle_salon, container, false);

        // Obtener datos de los argumentos
        String nombre = getArguments().getString(ARG_NOMBRE);
        String ubicacion = getArguments().getString(ARG_UBICACION);
        String telefono = getArguments().getString(ARG_TELEFONO);
        String horario = getArguments().getString(ARG_HORARIO);
        String[] servicios = getArguments().getStringArray(ARG_SERVICIOS);

        // Referencias a los TextViews
        TextView textViewNombre = view.findViewById(R.id.textViewNombre);
        TextView textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        TextView textViewTelefono = view.findViewById(R.id.textViewTelefono);
        TextView textViewHorario = view.findViewById(R.id.textViewHorario);
        ListView listViewServicios = view.findViewById(R.id.listViewServicios);

        // Establecer los textos
        textViewNombre.setText(nombre);
        textViewUbicacion.setText(ubicacion);
        textViewTelefono.setText(telefono);
        textViewHorario.setText(horario);

        // Configurar el ListView de servicios
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, servicios);
        listViewServicios.setAdapter(adapter);

        // Listener para seleccionar un servicio
        listViewServicios.setOnItemClickListener((parent, view1, position, id) -> {
            String servicioSeleccionado = servicios[position];

            // Crear una instancia del DetalleServicioFragment con el servicio seleccionado
            DetalleServicioFragment detalleServicioFragment = DetalleServicioFragment.newInstance(
                    nombre, ubicacion, telefono, servicioSeleccionado);

            // Reemplazar el fragmento actual con DetalleServicioFragment
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detalleServicioFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }}