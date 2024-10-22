package com.example.appp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistorialFragment extends Fragment {

    private ListView listViewHistorial;
    private ReservaAdapter adapter;
    private List<Reserva> listaReservas; // Cambia a una lista de objetos Reserva
    private FirebaseFirestore db;
    private String uid;  // UID del usuario autenticado

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.activity_historial, container, false);

        // Inicializar la vista y la lógica del historial
        listViewHistorial = view.findViewById(R.id.listViewHistorial);
        db = FirebaseFirestore.getInstance();
        listaReservas = new ArrayList<>();
        adapter = new ReservaAdapter(getContext(), listaReservas); // Usar el nuevo adaptador
        listViewHistorial.setAdapter(adapter);

        // Obtener el UID del usuario autenticado
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (uid != null) {
            // Llamamos al método para obtener las reservas del usuario
            obtenerReservasUsuario(uid);
        } else {
            Toast.makeText(getContext(), "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void obtenerReservasUsuario(String userId) {
        // Referencia al documento del usuario en la colección "usuarios"
        DocumentReference usuarioDocRef = db.collection("usuarios").document(userId);

        // Consultar las reservas dentro del documento del usuario autenticado
        usuarioDocRef.collection("reservas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaReservas.clear(); // Limpiamos la lista antes de agregar nuevos datos
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String id = document.getId(); // Obtener el ID del documento
                            String nombreEmpresa = document.getString("nombreEmpresa");
                            String ubicacion = document.getString("ubicacion");
                            String horario = document.getString("horario");
                            String contacto = document.getString("nombreContacto");

                            // Crear una nueva instancia de Reserva
                            Reserva reserva = new Reserva(id, nombreEmpresa, ubicacion, horario, contacto, "", ""); // Ajusta según sea necesario
                            listaReservas.add(reserva);  // Agregamos la reserva a la lista
                        }
                        adapter.notifyDataSetChanged();  // Actualizamos el adaptador
                    } else {
                        Toast.makeText(getContext(), "Error al obtener reservas", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
