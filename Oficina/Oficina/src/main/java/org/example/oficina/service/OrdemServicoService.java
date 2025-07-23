package org.example.oficina.service;

import org.example.oficina.dao.CargoDAO;
import org.example.oficina.dao.OrdemServicoDAO;
import org.example.oficina.dao.ProdutoUtilizadoDAO;
import org.example.oficina.dao.ServicoDAO;
import org.example.oficina.model.*;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.OrdemServicoValidator;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrdemServicoService {

    private final OrdemServicoValidator ordemServicoValidator = new OrdemServicoValidator();

    public boolean salvarOrdemServico(OrdemServico ordemServico) throws SQLException {
        List<String> erros = ordemServicoValidator.validarAtributos(ordemServico);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            return ordemServicoDAO.create(ordemServico);
        }
    }

    public boolean atualizarOrdemServico(OrdemServico ordemServico) throws SQLException{
        List<String> erros = ordemServicoValidator.validarAtributos(ordemServico);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            return ordemServicoDAO.update(ordemServico);
        }
    }

    public boolean deletarCargo(int id) throws SQLException{
        try(Connection conn = DatabaseConnection.getConnection()){
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            return ordemServicoDAO.delete(id);
        }
    }

    public Optional<OrdemServico> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            return ordemServicoDAO.findById(id);
        }
    }

    public List<Cargo> listarTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CargoDAO cargoDAO = new CargoDAO(conn);
            return cargoDAO.findAll();
        }
    }

    public boolean atualizarValorTotal(int idOrdemServico) throws SQLException {
        BigDecimal valorTotal = BigDecimal.ZERO;

        try (Connection conn = DatabaseConnection.getConnection()) {
            ServicoDAO servicoDAO = new ServicoDAO(conn);
            ProdutoUtilizadoDAO produtoUtilizadoDAO = new ProdutoUtilizadoDAO(conn);
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);

            List<Servico> servicos = servicoDAO.findByOrdemServico(idOrdemServico);
            List<ProdutoUtilizado> produtoUtilizados = produtoUtilizadoDAO.findByOrdemServico(idOrdemServico);

            for (Servico servico : servicos) {
                if (servico.getValor() != null) {
                    valorTotal = valorTotal.add(servico.getValor());
                }
            }

            for (ProdutoUtilizado produtoUtilizado : produtoUtilizados) {
                if (produtoUtilizado.getValorTotal() != null) {
                    valorTotal = valorTotal.add(produtoUtilizado.getValorTotal());
                }
            }

            Optional<OrdemServico> ordemServicoOptional = ordemServicoDAO.findById(idOrdemServico);
            if (ordemServicoOptional.isPresent()) {
                OrdemServico ordemServico = ordemServicoOptional.get();
                ordemServico.setValorTotal(valorTotal);
                ordemServicoDAO.update(ordemServico);
                return true;
            } else {
                return false;
            }
        }
    }

}
