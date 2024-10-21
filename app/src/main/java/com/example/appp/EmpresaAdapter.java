package com.example.appp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder> {

    private List<Empresa> empresaList = new ArrayList<>();

    private OnItemClickListener listener;

    // Constructor que recibe la lista de empresas y el listener para manejar los eventos de click
    public EmpresaAdapter(List<Empresa> empresaList, OnItemClickListener listener) {
        this.empresaList = empresaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout para cada elemento de la lista (item_empresa.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empresa, parent, false);
        return new EmpresaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder holder, int position) {
        // Obtiene la empresa actual de la lista
        Empresa empresa = empresaList.get(position);

        // Asigna los valores a los TextViews de nombre
        holder.tvNombreEmpresa.setText(empresa.getNombre());


        // Botón Editar
        holder.btnEditar.setOnClickListener(v -> listener.onEditarClick(empresa));

        // Botón Eliminar -
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminarClick(empresa));
    }

    @Override
    public int getItemCount() {
        // Devuelve el tamaño de la lista de empresas
        return empresaList != null ? empresaList.size() : 0;

    }

    // ViewHolder que almacena las referencias a las vistas para cada elemento de la lista
    public static class EmpresaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreEmpresa;
        Button btnEditar, btnEliminar;

        public EmpresaViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializa las vistas
            tvNombreEmpresa = itemView.findViewById(R.id.tvNombreEmpresa);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    // Interfaz para manejar los eventos de click en Editar y Eliminar
    public interface OnItemClickListener {
        void onEditarClick(Empresa empresa);
        void onEliminarClick(Empresa empresa);
    }
}

