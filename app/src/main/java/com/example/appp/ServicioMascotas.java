package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ServicioMascotas extends AppCompatActivity {

    private ListView listViewMascotas;
    private FirebaseFirestore db;
    private ArrayAdapter<String> adapter;
    private List<String> listaEmpresas;
    private List<String> listaIdsEmpresas;  // Lista para almacenar los IDs de las empresas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_mascotas);

        listViewMascotas = findViewById(R.id.listviewmascotas);
        db = FirebaseFirestore.getInstance();
        listaEmpresas = new ArrayList<>();
        listaIdsEmpresas = new ArrayList<>();  // Inicializamos la lista de IDs
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaEmpresas);
        listViewMascotas.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this::handleBottomNavigation);

        obtenerEmpresasMascotas();

        // Listener para manejar la selección de un servicio de mascotas y mostrar los detalles
        listViewMascotas.setOnItemClickListener((parent, view, position, id) -> {
            String empresaId = listaIdsEmpresas.get(position);  // Obtenemos el ID de la empresa seleccionada

            // Hacemos una consulta a Firestore para obtener los detalles del servicio de mascotas seleccionado
            db.collection("prestadores")
                    .document("empresa")
                    .collection("Empresa")
                    .document(empresaId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String nombre = documentSnapshot.getString("nombre");
                            String ubicacion = documentSnapshot.getString("ubicacion");
                            String telefono = documentSnapshot.getString("telefono");
                            String horario = documentSnapshot.getString("horario");

                            // Verificamos si el campo "servicios" es una lista o una cadena
                            Object serviciosObject = documentSnapshot.get("servicios");
                            String[] servicios;

                            if (serviciosObject instanceof List) {
                                // Si es una lista, convertimos la lista a un array de String
                                List<String> serviciosList = (List<String>) serviciosObject;
                                servicios = serviciosList.toArray(new String[0]);
                            } else if (serviciosObject instanceof String) {
                                // Si es una cadena, convertimos esa cadena a un array de un solo elemento
                                servicios = new String[]{(String) serviciosObject};
                            } else {
                                // Si no hay servicios o es de otro tipo, inicializamos un array vacío
                                servicios = new String[]{};
                            }

                            // Crear una instancia de DetalleSalonFragment con los datos obtenidos de Firestore
                            DetalleSalonFragment detalleFragment = DetalleSalonFragment.newInstance(
                                    nombre, ubicacion, telefono, horario, servicios);

                            // Reemplazar la actividad actual con el fragmento de detalle
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, detalleFragment)
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            Toast.makeText(this, "No se encontraron detalles para este servicio de mascotas", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al obtener detalles: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }

    private void obtenerEmpresasMascotas() {
        db.collection("prestadores")
                .document("empresa")
                .collection("Empresa")
                .whereEqualTo("categoria", "Servicios de Mascotas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaEmpresas.clear();
                        listaIdsEmpresas.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String nombreEmpresa = document.getString("nombre");
                            listaEmpresas.add(nombreEmpresa);
                            listaIdsEmpresas.add(document.getId());
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error al obtener servicios", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            return true;
        } else if (item.getItemId() == R.id.bottom_perfil) {
            startActivity(new Intent(this, Perfil.class));
            finish();
            return true;
        }
        return false;
    }
}

