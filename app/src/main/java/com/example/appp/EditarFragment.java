package com.example.appp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditarFragment extends Fragment {

    private EditText etNombre, etUbicacion, etTelefono, etHorario;
    private Button btnGuardar;
    private String empresaId;

    public static EditarFragment newInstance(String empresaId) {
        EditarFragment fragment = new EditarFragment();
        Bundle args = new Bundle();
        args.putString("empresaId", empresaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            empresaId = getArguments().getString("empresaId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        etNombre = view.findViewById(R.id.etNombre);
        etUbicacion = view.findViewById(R.id.etUbicacion);
        etTelefono = view.findViewById(R.id.etTelefono);
        etHorario = view.findViewById(R.id.etHorario);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        // Aquí cargarás los datos de la empresa según su ID
        cargarDatosEmpresa();

        // Guardar cambios en Firebase o base de datos
        btnGuardar.setOnClickListener(v -> guardarCambios());

        return view;
    }

    private void cargarDatosEmpresa() {
        // Lógica para cargar los datos de la empresa según empresaId
    }

    private void guardarCambios() {
        // Lógica para guardar los cambios en Firebase o base de datos
    }
}
