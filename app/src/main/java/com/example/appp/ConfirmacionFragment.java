package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConfirmacionFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_UBICACION = "ubicacion";
    private static final String ARG_TELEFONO = "telefono";
    private static final String ARG_HORARIO = "horario";

    private EditText editTextNombre;
    private EditText editTextCorreo;
    private EditText editTextTelefono;
    private Button buttonConfirmar;

    // Variables para los datos de la reserva
    private String nombreEmpresa;
    private String ubicacion;
    private String telefono;
    private String horario;

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
            nombreEmpresa = getArguments().getString(ARG_NOMBRE);
            ubicacion = getArguments().getString(ARG_UBICACION);
            telefono = getArguments().getString(ARG_TELEFONO);
            horario = getArguments().getString(ARG_HORARIO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_confirmacion, container, false);

        // Inicializa los TextViews para mostrar los datos de la reserva
        TextView textViewNombreEmpresa = view.findViewById(R.id.textViewNombre);
        TextView textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        TextView textViewTelefono = view.findViewById(R.id.textViewTelefono);
        TextView textViewHorario = view.findViewById(R.id.textViewHorario);

        // Muestra los datos de la empresa
        textViewNombreEmpresa.setText(nombreEmpresa);
        textViewUbicacion.setText(ubicacion);
        textViewTelefono.setText(telefono);
        textViewHorario.setText(horario);

        // Inicializa los EditTexts y el botón
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextCorreo = view.findViewById(R.id.editTextCorreo);
        editTextTelefono = view.findViewById(R.id.editTextTelefono);
        buttonConfirmar = view.findViewById(R.id.buttonConfirmar);

        // Configura el botón para guardar la reserva
        buttonConfirmar.setOnClickListener(v -> guardarReserva());

        return view;
    }

    private void guardarReserva() {
        String nombreContacto = editTextNombre.getText().toString();
        String correoContacto = editTextCorreo.getText().toString();
        String telefonoContacto = editTextTelefono.getText().toString();

        // Validar que los campos no estén vacíos
        if (nombreContacto.isEmpty() || correoContacto.isEmpty() || telefonoContacto.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crea una nueva instancia de la reserva sin el ID inicial
        Reserva nuevaReserva = new Reserva("", nombreEmpresa, ubicacion, horario, nombreContacto, correoContacto, telefonoContacto);

        // Guardar en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Usar el método add para guardar y obtener el documentReference
        db.collection("usuarios").document(userId).collection("reservas")
                .add(nuevaReserva)
                .addOnSuccessListener(documentReference -> {
                    // Almacena el ID de la reserva en el objeto Reserva
                    String idReserva = documentReference.getId();
                    nuevaReserva.setId(idReserva); // Asegúrate de tener un método setId en la clase Reserva

                    // Mostrar mensaje de éxito
                    Toast.makeText(getContext(), "Reserva confirmada exitosamente", Toast.LENGTH_LONG).show();

                    // Usar un Handler para esperar 1500 ms antes de redirigir al usuario
                    new Handler().postDelayed(() -> {
                        // Redirigir al usuario a la pantalla de inicio
                        Intent intent = new Intent(getActivity(), InicioUsuario.class);
                        startActivity(intent);

                        // Cerrar la actividad actual
                        getActivity().finish();
                    }, 1500); // Espera de 1.5 segundos

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error al guardar la reserva: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
