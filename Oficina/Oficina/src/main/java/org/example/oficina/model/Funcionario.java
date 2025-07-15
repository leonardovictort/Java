package org.example.oficina.model;

public class Funcionario {

    private int id;
    private String nome;
    private String cpf;
    private Cargo cargo;

    public Funcionario() {

    }

    public Funcionario(String nome, String cpf, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Funcionario(int id, String nome, String cpf, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getDescricao() {
        return cargo.getDescricao();
    }
}

