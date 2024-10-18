package com.example.appp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class EmpresaAdapter extends ArrayAdapter<Empresa> {
    private final Context context;
    private final List<Empresa> empresas;

    public EmpresaAdapter(Context context, List<Empresa> empresas) {
        super(context, R.layout.item_empresa, empresas);
        this.context = context;
        this.empresas = empresas;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.item_empresa, parent, false);

        TextView textViewNombre = rowView.findViewById(R.id.textViewNombre);
        textViewNombre.setText(empresas.get(position).getNombre());

        return rowView;
    }
}

