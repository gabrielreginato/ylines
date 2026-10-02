package com.example.ylines;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTxtPesquisar;
    Button btnFiltroNome;
    Button btnFiltroData;

    ListView listViewContatos;

    Button btnAddContato;

    private void _abrirEditarContato(Contato contato) {
        Intent intent = new Intent(MainActivity.this, EditarContato.class);
        intent.putExtra("nome", contato.getNome());
        intent.putExtra("telefone", contato.getTelefone());
        intent.putExtra("endereco", contato.getEndereco());
        intent.putExtra("cidade", contato.getCidade());
        intent.putExtra("uf", contato.getUf());
        startActivity(intent);
    }

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

        // Contatos de teste
        ArrayList<Contato> contatos = new ArrayList<>();

        contatos.add(new Contato(
                "Ana Souza",
                "(45) 98812-3456",
                "Rua das Palmeiras, 456 - Centro",
                "Cascavel",
                "PR"
        ));

        contatos.add(new Contato(
                "Bruno Lima",
                "(41) 98765-4321",
                "Rua das Flores, 123 - Centro",
                "Curitiba",
                "PR"
        ));

        contatos.add(new Contato(
                "Carla Mendes",
                "(11) 91234-5678",
                "Av. Paulista, 1000 - São Paulo/SP",
                "São Paulo",
                "SP"
        ));

        contatos.add(new com.example.ylines.Contato(
                "Diego Pereira",
                "(47) 99876-5432",
                "Rua XV de Novembro, 500 - Blumenau/SC",
                "Blumenau",
                "SC"
        ));

        // Adapter
        ContatoAdapter adapter = new ContatoAdapter(
                MainActivity.this,
                contatos
        );

        listViewContatos.setAdapter(adapter);

        
    }
}