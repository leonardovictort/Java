package org.example.oficina.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Servico {
    private int id;
    private String descricao;
    private BigDecimal valor;
    private Funcionario mecanico;
    private boolean concluido;
    private String observacao;
    private OrdemServico ordemServico;

    public Servico() {

    }

    public Servico(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servico(int id, String descricao, BigDecimal valor, Funcionario mecanico, boolean concluido, String observacao, OrdemServico ordemServico) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.mecanico = mecanico;
        this.concluido = concluido;
        this.observacao = observacao;
        this.ordemServico = ordemServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Funcionario getMecanico() {
        return mecanico;
    }

    public void setMecanico(Funcionario mecanico) {
        this.mecanico = mecanico;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servico servico)) return false;
        return getId() == servico.getId() && isConcluido() == servico.isConcluido() && Objects.equals(getDescricao(), servico.getDescricao()) && Objects.equals(getValor(), servico.getValor()) && Objects.equals(getMecanico(), servico.getMecanico()) && Objects.equals(getObservacao(), servico.getObservacao()) && Objects.equals(getOrdemServico(), servico.getOrdemServico());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getValor(), getMecanico(), isConcluido(), getObservacao(), getOrdemServico());
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", mecanico=" + mecanico +
                ", concluido=" + concluido +
                ", observacao='" + observacao + '\'' +
                ", ordemServico=" + ordemServico +
                '}';
    }

}
