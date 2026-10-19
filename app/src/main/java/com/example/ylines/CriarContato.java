package com.example.ylines;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CriarContato extends AppCompatActivity {

    Button btnVoltar;

    EditText edtTxtNome;
    EditText edtTxtTelefone;
    EditText edtTxtEndereco;
    EditText edtTxtCidade;
    EditText edtTxtUf;

    Button btnSalvar;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_criar_contato);

        btnVoltar = findViewById(R.id.btnVoltar);

        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtTelefone = findViewById(R.id.edtTxtTelefone);
        edtTxtEndereco = findViewById(R.id.edtTxtEndereco);
        edtTxtCidade = findViewById(R.id.edtTxtCidade);
        edtTxtUf = findViewById(R.id.edtTxtUf);

        btnSalvar = findViewById(R.id.btnSalvar);

        dbHelper = new DatabaseHelper(this);

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(CriarContato.this, MainActivity.class);
            startActivity(intent);
        });

        btnSalvar.setOnClickListener(v -> {
            String nome = edtTxtNome.getText().toString();
            String telefone = edtTxtTelefone.getText().toString();
            String endereco = edtTxtEndereco.getText().toString();
            String cidade = edtTxtCidade.getText().toString();
            String uf = edtTxtUf.getText().toString();

            dbHelper.inserirContato(nome, telefone, endereco, cidade, uf);

            Toast.makeText(CriarContato.this, "Contato adicionado!", Toast.LENGTH_SHORT).show();

            atualizarListaContatos();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void atualizarListaContatos() {
        Intent intent = new Intent(CriarContato.this, MainActivity.class);
        startActivity(intent);
    }
}