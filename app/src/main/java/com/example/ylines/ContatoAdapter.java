package com.example.ylines;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.example.ylines.Contato;

public class ContatoAdapter extends BaseAdapter {
    private Context context;
    private List<Contato> contatos;

    public ContatoAdapter(Context context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contato, parent, false);
        }

        Contato contato = contatos.get(position);

        TextView txtNome = convertView.findViewById(R.id.txtNome);
        TextView txtTelefone = convertView.findViewById(R.id.txtTelefone);
        ImageButton btnEditar = convertView.findViewById(R.id.btnEditar);
        ImageButton btnVisualizar = convertView.findViewById(R.id.btnVisualizar);
        LinearLayout linearLayoutContato = convertView.findViewById(R.id.linearLayoutContato);

        txtNome.setText(contato.getNome());
        txtTelefone.setText(contato.getTelefone());

       btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditarContato.class);
            intent.putExtra("nome", contato.getNome());
            intent.putExtra("telefone", contato.getTelefone());
            intent.putExtra("endereco", contato.getEndereco());
            intent.putExtra("cidade", contato.getCidade());
            intent.putExtra("uf", contato.getUf());
            context.startActivity(intent);
        });

        btnVisualizar.setOnClickListener(v -> {
            Intent intent = new Intent(context, VerContato.class);
            intent.putExtra("nome", contato.getNome());
            intent.putExtra("telefone", contato.getTelefone());
            intent.putExtra("endereco", contato.getEndereco());
            intent.putExtra("cidade", contato.getCidade());
            intent.putExtra("uf", contato.getUf());
            context.startActivity(intent);
        });

        linearLayoutContato.setOnClickListener(v -> {
            Intent intent = new Intent(context, VerContato.class);
            intent.putExtra("nome", contato.getNome());
            intent.putExtra("telefone", contato.getTelefone());
            intent.putExtra("endereco", contato.getEndereco());
            intent.putExtra("cidade", contato.getCidade());
            intent.putExtra("uf", contato.getUf());
            context.startActivity(intent);
        });


        return convertView;
    }
}