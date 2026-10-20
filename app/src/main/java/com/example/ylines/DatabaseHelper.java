package com.example.ylines;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Contatos.db";
    private static final int VERSAO = 1;

    private static final String TABELA_CONTATOS = "tb_contatos";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_ENDERECO = "endereco";
    private static final String COLUNA_CIDADE = "cidade";
    private static final String COLUNA_UF = "uf";

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarTabela = "CREATE TABLE " + TABELA_CONTATOS + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT, " +
                COLUNA_TELEFONE + " TEXT, " +
                COLUNA_ENDERECO + " TEXT, " +
                COLUNA_CIDADE + " TEXT, " +
                COLUNA_UF + " TEXT)";
        db.execSQL(criarTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONTATOS);
        onCreate(db);
    }

    public void inserirContato(String nome, String telefone, String endereco, String cidade, String uf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(COLUNA_NOME, nome);
        valores.put(COLUNA_TELEFONE, telefone);
        valores.put(COLUNA_ENDERECO, endereco);
        valores.put(COLUNA_CIDADE, cidade);
        valores.put(COLUNA_UF, uf);

        db.insert(TABELA_CONTATOS, null, valores);
        db.close();
    }

    public List<Contato> buscarTodos() {
        List<Contato> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABELA_CONTATOS;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                String telefone = cursor.getString(2);
                String endereco = cursor.getString(3);
                String cidade = cursor.getString(4);
                String uf = cursor.getString(5);

                lista.add(new Contato(id, nome, telefone, endereco, cidade, uf));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public List<Contato> buscarTodosOrdenadosPorNome() {
        List<Contato> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABELA_CONTATOS + " ORDER BY " + COLUNA_NOME + " ASC";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                String telefone = cursor.getString(2);
                String endereco = cursor.getString(3);
                String cidade = cursor.getString(4);
                String uf = cursor.getString(5);

                lista.add(new Contato(id, nome, telefone, endereco, cidade, uf));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public List<Contato> buscarTodosOrdenadosPorData() {
        List<Contato> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABELA_CONTATOS + " ORDER BY " + COLUNA_ID + " DESC";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                String telefone = cursor.getString(2);
                String endereco = cursor.getString(3);
                String cidade = cursor.getString(4);
                String uf = cursor.getString(5);

                lista.add(new Contato(id, nome, telefone, endereco, cidade, uf));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public List<Contato> buscarContatosPorNome(String nome) {
        List<Contato> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABELA_CONTATOS + " WHERE " + COLUNA_NOME + " LIKE ?";
        Cursor cursor = db.rawQuery(query, new String[]{"%" + nome + "%"});

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nomeContato = cursor.getString(1);
                String telefone = cursor.getString(2);
                String endereco = cursor.getString(3);
                String cidade = cursor.getString(4);
                String uf = cursor.getString(5);

                lista.add(new Contato(id, nomeContato, telefone, endereco, cidade, uf));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public void deletarTodosOsEstudos() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CONTATOS, null, null);

        db.close();
    }

    public void deletarContato(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CONTATOS, COLUNA_ID + " = ?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void atualizarContato(int id, String nome, String telefone, String endereco, String cidade, String uf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(COLUNA_NOME, nome);
        valores.put(COLUNA_TELEFONE, telefone);
        valores.put(COLUNA_ENDERECO, endereco);
        valores.put(COLUNA_CIDADE, cidade);
        valores.put(COLUNA_UF, uf);

        db.update(TABELA_CONTATOS, valores, COLUNA_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
