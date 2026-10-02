package com.example.ylines;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerContato extends AppCompatActivity {
    Button btnVoltar;

    Button btnLigar;
    Button btnMapa;
    ImageButton btnCopiarTelefone;
    ImageButton btnCopiarEndereco;

    TextView txtNome;
    TextView txtTelefone;
    TextView txtEndereco;
    TextView txtCidadeUf;

    String nome;
    String telefone;
    String endereco;
    String cidade;
    String uf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_contato);

        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEndereco = findViewById(R.id.txtEndereco);
        txtCidadeUf = findViewById(R.id.txtCidadeUf);

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

        Intent intent = getIntent();

        nome = intent.getStringExtra("nome");
        telefone = intent.getStringExtra("telefone");
        endereco = intent.getStringExtra("endereco");
        cidade = intent.getStringExtra("cidade");
        uf = intent.getStringExtra("uf");

        txtNome.setText(nome);
        txtTelefone.setText(telefone);
        txtEndereco.setText(endereco);
        
        if(endereco.length() > 24) {
            txtEndereco.setText(endereco.substring(0, 24) + "...");
        }
        
        txtCidadeUf.setText(cidade + "/" + uf);
    }
}