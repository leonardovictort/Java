package org.example.oficina.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Cargo {

    private int id;
    private String nome;
    private String descricao;
    private BigDecimal salario;

    public Cargo() {
    }

    public Cargo(int id, String nome, String descricao, BigDecimal salario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cargo cargo)) return false;
        return getId() == cargo.getId() && Objects.equals(getNome(), cargo.getNome()) && Objects.equals(getDescricao(), cargo.getDescricao()) && Objects.equals(getSalario(), cargo.getSalario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDescricao(), getSalario());
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", salario=" + salario +
                '}';
    }
}
