        package com.example.appp;
        
        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Toast;

        import androidx.activity.EdgeToEdge;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.Objects;

        public class Registro extends AppCompatActivity {

            // Variables para Firebase
            private FirebaseAuth mAuth;
            private FirebaseFirestore db;

            // Elementos de la interfaz
            private EditText editTextFullName;
            private EditText editTextEmail;
            private EditText editTextPassword;
            private RadioGroup radioGroup;
            public RadioButton radioButtonPrestador, radioButtonUsuario;
            public Button registerButton;

            @SuppressLint("MissingInflatedId")
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                EdgeToEdge.enable(this);
                setContentView(R.layout.activity_registro);
        
                View mainView = findViewById(R.id.registro);

                // Inicializar Firebase Authentication y Firestore
                mAuth = FirebaseAuth.getInstance();
                db = FirebaseFirestore.getInstance();

                editTextFullName = findViewById(R.id.editTextFullName);
                editTextEmail = findViewById(R.id.editTextEmail);
                editTextPassword = findViewById(R.id.editTextPassword);
                radioGroup = findViewById(R.id.radioGroup);
                radioButtonPrestador = findViewById(R.id.radioButtonPrestador);
                radioButtonUsuario = findViewById(R.id.radioButtonUsuario);
                registerButton = findViewById(R.id.registerButton);

                registerButton.setOnClickListener(v -> registerUsuario());
            }

            private void registerUsuario() {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String name = editTextFullName.getText().toString().trim();
                boolean isServiceProvider = getUserType();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Usuario registrado con éxito
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                if (currentUser != null) {
                                    // Guardar datos adicionales en Firestore
                                    String userId = currentUser.getUid();
                                    GuardarUsuario(userId, name, email, isServiceProvider);
                                }
                            } else {
                                // Mostrar error de registro
                                Toast.makeText(this, "Error al registrar: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }


            private boolean getUserType() {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                return radioButton != null && radioButton.getText().toString().equals("Prestador de servicios");
            }

            private void GuardarUsuario(String userId, String name, String email, boolean isServiceProvider) {
                Map<String, Object> user = new HashMap<>();
                user.put("name", name);
                user.put("email", email);

                String collection = isServiceProvider ? "prestadores" : "usuarios";

                // Guardar los datos en Firestore
                db.collection(collection)
                        .document(userId)
                        .set(user)
                        .addOnSuccessListener(aVoid -> {
                            // Registro exitoso
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(() -> {
                                Intent intent = new Intent(Registro.this, Login.class);
                                startActivity(intent);
                                finish();
                                }, 1500);
                        })
                        .addOnFailureListener(e -> {
                            // Error al guardar los datos
                            Toast.makeText(this, "Error al guardar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        }


