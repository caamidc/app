package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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
        TextView textViewNombreServicio = view.findViewById(R.id.textViewNombreServicio);
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
            horarioSeleccionado = horariosList.get(position);

            // Navegar a ConfirmacionFragment
            FragmentManager fragmentManager = getParentFragmentManager();
            ConfirmacionFragment confirmacionFragment = ConfirmacionFragment.newInstance(nombre, ubicacion, telefono, horarioSeleccionado);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, confirmacionFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void cargarHorariosDisponibles(String fecha) {
        horariosList.clear();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("prestadores").document("empresa")
                .collection("Empresa")
                .whereEqualTo("nombre", nombre) // Cambia esto según cómo busques la empresa
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && !task.getResult().isEmpty()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Empresa empresa = document.toObject(Empresa.class);
                            String horarioApertura = empresa.getHorarioApertura();
                            String horarioCierre = empresa.getHorarioCierre();
                            generarHorariosDisponibles(horarioApertura, horarioCierre);
                        }
                    } else {
                        Toast.makeText(getContext(), "No se encontró la empresa", Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged(); // Notificar que el adapter ha cambiado
                });
    }

    private void generarHorariosDisponibles(String apertura, String cierre) {
        String[] aperturaSplit = apertura.split(":");
        String[] cierreSplit = cierre.split(":");

        int horaApertura = Integer.parseInt(aperturaSplit[0]);
        int horaCierre = Integer.parseInt(cierreSplit[0]);

        for (int i = horaApertura; i <= horaCierre; i++) {
            horariosList.add(i + ":00 AM"); // Cambia AM por PM si es necesario
        }
    }

    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            return true;
        } else if (item.getItemId() == R.id.bottom_perfil) {
            startActivity(new Intent(getActivity(), Perfil.class));
            getActivity().finish();
            return true;
        }
        return false;
    }
}