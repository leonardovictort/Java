package org.example.oficina.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoUtilizado {

    private int id;
    private Categoria categoria;
    private String marca;
    private String descricao;
    private BigDecimal custoUnitario;
    private BigDecimal precoVenda;
    private int quantidade;
    private BigDecimal valorTotal;
    private OrdemServico ordemServico;

    public ProdutoUtilizado(Categoria categoria, String marca, String descricao, BigDecimal custoUnitario, BigDecimal precoVenda, int quantidade) {
        this.categoria = categoria;
        this.marca = marca;
        this.descricao = descricao;
        this.custoUnitario = custoUnitario;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    public ProdutoUtilizado(int id, Categoria categoria, String marca, String descricao, BigDecimal custoUnitario, BigDecimal precoVenda,
                            int quantidade, BigDecimal valorTotal, OrdemServico ordemServico) {
        this.id = id;
        this.categoria = categoria;
        this.marca = marca;
        this.descricao = descricao;
        this.custoUnitario = custoUnitario;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.ordemServico = ordemServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(BigDecimal custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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
        if (!(o instanceof ProdutoUtilizado that)) return false;
        return getId() == that.getId() && getQuantidade() == that.getQuantidade() && getCategoria() == that.getCategoria() && Objects.equals(getMarca(), that.getMarca()) && Objects.equals(getDescricao(), that.getDescricao()) && Objects.equals(getCustoUnitario(), that.getCustoUnitario()) && Objects.equals(getPrecoVenda(), that.getPrecoVenda()) && Objects.equals(getValorTotal(), that.getValorTotal()) && Objects.equals(getOrdemServico(), that.getOrdemServico());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategoria(), getMarca(), getDescricao(), getCustoUnitario(), getPrecoVenda(), getQuantidade(), getValorTotal(), getOrdemServico());
    }

    @Override
    public String toString() {
        return "ProdutoUtilizado{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", marca='" + marca + '\'' +
                ", descricao='" + descricao + '\'' +
                ", custoUnitario=" + custoUnitario +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                ", ordemServico=" + ordemServico +
                '}';
    }
}
