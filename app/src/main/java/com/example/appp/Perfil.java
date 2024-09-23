package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ListView opcionList = findViewById(R.id.optionsList);
        opcionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(Perfil.this, EditarPerfil.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(Perfil.this,Historial.class);
                    startActivity(intent);
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_perfil);

        //menu navegacion
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_perfil) {
                return true;
            } else if (item.getItemId() == R.id.bottom_home) {
                Intent intentPerfil = new Intent(this, Inicio.class);
                startActivity(intentPerfil);
                finish();
                return true;
            }
            return false;
        });
    }
}