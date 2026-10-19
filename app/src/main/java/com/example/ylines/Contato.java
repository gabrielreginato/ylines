package com.example.ylines;

public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private String cidade;
    private String uf;

    public Contato(int id, String nome, String telefone, String endereco, String cidade, String uf) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }   

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public int getId() { return id; }

    /*public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }*/
}