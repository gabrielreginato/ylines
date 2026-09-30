package com.example.ylines;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerContato extends AppCompatActivity {
    Button btnVoltar;

    Button btnLigar;
    Button btnMapa;
    Button btnCopiarTelefone;
    Button btnCopiarEndereco;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_contato);

        btnVoltar = findViewById(R.id.btnVoltar);

        btnLigar = findViewById(R.id.btnLigar);
        btnMapa = findViewById(R.id.btnMapa);

        btnCopiarTelefone = findViewById(R.id.btnCopiarTelefone);
        btnCopiarEndereco = findViewById(R.id.btnCopiarEndereco);

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(VerContato.this, MainActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}