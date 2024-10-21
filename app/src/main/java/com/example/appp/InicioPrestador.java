package com.example.appp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class InicioPrestador extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmpresaAdapter adapter;
    private List<Empresa> listaEmpresas;
    private FloatingActionButton fabAddEmpresa;
    private FirebaseFirestore db;
    private String uid; // Almacenar el UID del prestador autenticado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_prestador);

        recyclerView = findViewById(R.id.recyclerViewEmpresas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabAddEmpresa = findViewById(R.id.fab_add_empresa);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Obtener UID del prestador autenticado

        // Inicializar lista de empresas
        listaEmpresas = new ArrayList<>();

        // Configurar el adaptador del RecyclerView
        adapter = new EmpresaAdapter(listaEmpresas, new EmpresaAdapter.OnItemClickListener() {
            @Override
            public void onEditarClick(Empresa empresa) {
                cargarFragmentEditar(empresa.getUidPrestador());
            }

            @Override
            public void onEliminarClick(Empresa empresa) {
                mostrarDialogoEliminar(empresa.getUidPrestador());
            }
        });
        recyclerView.setAdapter(adapter);

        // Cargar empresas desde Firebase Firestore
        cargarEmpresas();

        // Configurar el botón flotante para agregar una nueva empresa
        fabAddEmpresa.setOnClickListener(v -> cargarFragmentAgregarEmpresa());

        // Configurar BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(this::handleBottomNavigation);
    }
    private boolean handleBottomNavigation(MenuItem item) {
        if (item.getItemId() == R.id.bottom_home) {
            return true; // No hacer nada si se selecciona "Inicio"
        } else if (item.getItemId() == R.id.bottom_perfil) {
            Intent intent = new Intent(this, Perfil.class);
            intent.putExtra("fromActivity", "InicioPrestador"); // Indicar que viene de InicioPrestador
            startActivity(intent);
            finish(); // Cerrar esta actividad si se navega al perfil
            return true;
        }
        return false;
    }

    private void cargarFragmentEditar(String empresaId) {
        // Lógica para cargar el fragmento de edición
        EditarFragment editarFragment = EditarFragment.newInstance(empresaId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, editarFragment)
                .addToBackStack(null)
                .commit();
    }

    private void cargarFragmentAgregarEmpresa() {
        // Iniciar la actividad RegistroServicio
        Intent intent = new Intent(InicioPrestador.this, RegistroServicio.class);
        startActivity(intent);
    }

    private void mostrarDialogoEliminar(String empresaId) {
        // Mostrar diálogo de confirmación para eliminar
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Seguro que quiere eliminar este servicio?")
                .setPositiveButton("Sí", (dialog, which) -> eliminarEmpresa(empresaId))
                .setNegativeButton("No", null)
                .show();
    }

    private void eliminarEmpresa(String empresaId) {
        db.collection("prestadores").document("empresa") // Mantén la misma referencia que usaste para registrar
                .collection("Empresa").document(empresaId).delete()
                .addOnSuccessListener(aVoid -> {
                    // Empresa eliminada, actualizar lista
                    cargarEmpresas();
                })
                .addOnFailureListener(e -> {
                    // Mostrar error en caso de falla
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("No se pudo eliminar la empresa.")
                            .setPositiveButton("OK", null)
                            .show();
                });
    }

    private void cargarEmpresas() {
        // Cargar las empresas desde Firebase Firestore
        db.collection("prestadores").document("empresa")
                .collection("Empresa")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaEmpresas.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Empresa empresa = document.toObject(Empresa.class);
                            empresa.setUidPrestador(document.getId()); // Asignar el ID del documento a la empresa
                            listaEmpresas.add(empresa);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        new AlertDialog.Builder(this)
                                .setTitle("Error")
                                .setMessage("No se pudo cargar las empresas.")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                });
    }}
