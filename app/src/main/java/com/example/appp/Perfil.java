package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Perfil extends AppCompatActivity {
    private String fromActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        fromActivity = getIntent().getStringExtra("fromActivity");

        ListView opcionList = findViewById(R.id.optionsList);
        opcionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(Perfil.this, EditarPerfil.class);
                    startActivity(intent);
                } else if (position == 1) {
                    // Reemplazar el Fragment actual con HistorialFragment
                    Fragment historialFragment = new HistorialFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, historialFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
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
                Intent intent;
                // Redirigir según la actividad de origen
                if ("InicioPrestador".equals(fromActivity)) {
                    intent = new Intent(this, InicioPrestador.class);
                } else {
                    intent = new Intent(this, InicioUsuario.class);
                }
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }
}