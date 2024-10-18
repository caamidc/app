package com.example.appp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {

    private List<Servicio> serviciosList;

    public ServicioAdapter(List<Servicio> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        holder.bind(serviciosList.get(position));
    }

    @Override
    public int getItemCount() {
        return serviciosList.size();
    }

    class ServicioViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(Servicio servicio) {
            textView.setText(servicio.getNombre());
        }
    }
}
