        package com.example.appp;
        
        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        
        import androidx.activity.EdgeToEdge;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.graphics.Insets;
        import androidx.core.view.ViewCompat;
        import androidx.core.view.WindowInsetsCompat;
        
        public class Registro extends AppCompatActivity {
        
            @SuppressLint("MissingInflatedId")
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                EdgeToEdge.enable(this);
                setContentView(R.layout.activity_registro);
        
                View mainView = findViewById(R.id.registro);
        
                if (mainView != null) {
                    // Establecer el padding para la vista principal
                    ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                        return insets;
                    });
                } else {
                    // Si 'mainView' es null, puedes imprimir un mensaje de error o manejarlo adecuadamente
                    throw new NullPointerException("El ConstraintLayout con ID 'registro' no se encontró.");
                }
        
                // Encontrar el botón de registrar por su ID
                Button registerButton = findViewById(R.id.registerButton);
        
                // Configurar el listener para manejar el clic en el botón
                registerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Crear un Intent para abrir la actividad MainActivity
                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        // Iniciar la actividad MainActivity
                        startActivity(intent);
                        // Finalizar la actividad actual
                        finish();
                    }
                });
            }
        }

