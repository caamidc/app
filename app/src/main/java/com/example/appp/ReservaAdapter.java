package com.example.appp;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ReservaAdapter extends ArrayAdapter<Reserva> {
    private List<Reserva> reservas;
    private Context context;

    public ReservaAdapter(@NonNull Context context, List<Reserva> reservas) {
        super(context, 0, reservas);
        this.reservas = reservas;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtener el objeto de reserva para esta posición
        Reserva reserva = reservas.get(position);

        // Inflar la vista si es necesario
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_reserva, parent, false);
        }

        // Referencias a las vistas
        TextView tvNombreEmpresa = convertView.findViewById(R.id.tvNombreEmpresa);
        TextView tvUbicacion = convertView.findViewById(R.id.tvUbicacion);
        TextView tvHorario = convertView.findViewById(R.id.tvHorario);
        TextView tvNombreContacto = convertView.findViewById(R.id.tvNombreContacto);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);

        // Configura los textos
        tvNombreEmpresa.setText(reserva.getNombreEmpresa());
        tvUbicacion.setText(reserva.getUbicacion());
        tvHorario.setText(reserva.getHorario());
        tvNombreContacto.setText(reserva.getNombreContacto());

        // Configura el botón de eliminar
        btnEliminar.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Confirmar eliminación")
                    .setMessage("¿Estás seguro de que deseas eliminar esta reserva?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        eliminarReserva(position);
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        return convertView;
    }

    private void eliminarReserva(int position) {
        // Lógica para eliminar la reserva de la lista y de Firestore
        Reserva reservaAEliminar = reservas.get(position);
        // Eliminar de Firestore usando el ID del documento de la reserva
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Aquí debes tener una referencia al documento de la reserva
        db.collection("usuarios").document(userId)
                .collection("reservas").document(reservaAEliminar.getId()) // Asumiendo que tienes un método getId()
                .delete()
                .addOnSuccessListener(aVoid -> {
                    reservas.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Reserva eliminada", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Error al eliminar la reserva", Toast.LENGTH_SHORT).show();
                });
    }
}

