package org.example.oficina.model;

import org.example.oficina.validator.ProdutoUtilizadoValidator;
import org.example.oficina.validator.ServicoValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdemServico {

    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private List<Servico> servicos;
    private List<ProdutoUtilizado> produtosUtilizados;
    private BigDecimal valorTotal;
    private LocalDate entrada;
    private LocalDate entrega;
    private StatusOS statusOS;
    private String observacoes;

    private final ServicoValidator servicoValidator = new ServicoValidator();
    private final ProdutoUtilizadoValidator produtoUtilizadoValidator = new ProdutoUtilizadoValidator();
    private OrdemServico ordemServico;

    public OrdemServico() {

    }


    public OrdemServico(Cliente cliente, Veiculo veiculo) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.entrada = LocalDate.now();
        this.statusOS = StatusOS.ABERTA;
        this.servicos = new ArrayList<>();
        this.produtosUtilizados = new ArrayList<>();
    }

    public OrdemServico(int id, Cliente cliente, Veiculo veiculo, List<Servico> servicos, List<ProdutoUtilizado> produtosUtilizados,
                        BigDecimal valorTotal, LocalDate entrada, LocalDate entrega, StatusOS statusOS, String observacoes) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.servicos = servicos;
        this.produtosUtilizados = produtosUtilizados;
        this.valorTotal = valorTotal;
        this.entrada = entrada;
        this.entrega = entrega;
        this.statusOS = statusOS;
        this.observacoes = observacoes;
    }

    public BigDecimal calcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Servico servico : this.servicos) {
            total = total.add(servico.getValor());
        }

        for (ProdutoUtilizado produtoUtilizado : this.produtosUtilizados) {
            BigDecimal subtotalProduto = produtoUtilizado.getPrecoVenda()
                    .multiply(BigDecimal.valueOf(produtoUtilizado.getQuantidade()));
            total = total.add(subtotalProduto);
        }

        this.valorTotal = total;
        return total;
    }

    public boolean adicionarServico(Servico servico) {
        if (ordemServico == null) {
            throw new IllegalStateException("Ordem de serviço não definida.");
        }

        List<String> erros = servicoValidator.validarAtributos(servico);
        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro(s) de validação no serviço:\n - " + String.join("\n - ", erros));
        }

        ordemServico.adicionarServico(servico);
        return true;
    }

    public boolean adicionarProdutoUtilizado(ProdutoUtilizado produtoUtilizado) {
        if (ordemServico == null) {
            throw new IllegalStateException("Ordem de serviço não definida.");
        }

        List<String> erros = produtoUtilizadoValidator.validarAtributos(produtoUtilizado);
        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro(s) de validação no produto utilizado:\n - " + String.join("\n - ", erros));
        }

        ordemServico.adicionarProdutoUtilizado(produtoUtilizado);
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<ProdutoUtilizado> getProdutosUtilizados() {
        return produtosUtilizados;
    }

    public void setProdutosUtilizados(List<ProdutoUtilizado> produtosUtilizados) {
        this.produtosUtilizados = produtosUtilizados;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDate entrada) {
        this.entrada = entrada;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

    public void setEntrega(LocalDate entrega) {
        this.entrega = entrega;
    }

    public StatusOS getStatusOS() {
        return statusOS;
    }

    public void setStatusOS(StatusOS statusOS) {
        this.statusOS = statusOS;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdemServico that)) return false;
        return getId() == that.getId() && Objects.equals(getCliente(), that.getCliente()) && Objects.equals(getVeiculo(), that.getVeiculo()) && Objects.equals(getServicos(), that.getServicos()) && Objects.equals(getProdutosUtilizados(), that.getProdutosUtilizados()) && Objects.equals(getValorTotal(), that.getValorTotal()) && Objects.equals(getEntrada(), that.getEntrada()) && Objects.equals(getEntrega(), that.getEntrega()) && getStatusOS() == that.getStatusOS() && Objects.equals(getObservacoes(), that.getObservacoes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCliente(), getVeiculo(), getServicos(), getProdutosUtilizados(), getValorTotal(), getEntrada(), getEntrega(), getStatusOS(), getObservacoes());
    }

    @Override
    public String toString() {
        return "OrdemServico{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", veiculo=" + veiculo +
                ", servicos=" + servicos +
                ", produtosUtilizados=" + produtosUtilizados +
                ", valorTotal=" + valorTotal +
                ", entrada=" + entrada +
                ", entrega=" + entrega +
                ", statusOS=" + statusOS +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
