package com.example.ylines;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtTxtPesquisar;
    Button btnFiltroNome;
    Button btnFiltroData;

    ListView listViewContatos;

    Button btnAddContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtTxtPesquisar = findViewById(R.id.edtTxtPesquisar);

        btnFiltroNome = findViewById(R.id.btnFiltroNome);
        btnFiltroData = findViewById(R.id.btnFiltroData);

        btnAddContato = findViewById(R.id.btnAddContato);

        listViewContatos = findViewById(R.id.listViewContatos);

        //Troca de tela
        btnAddContato.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CriarContato.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}