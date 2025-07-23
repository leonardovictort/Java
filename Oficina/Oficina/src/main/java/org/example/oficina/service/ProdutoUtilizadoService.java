package org.example.oficina.service;

import org.example.oficina.dao.OrdemServicoDAO;
import org.example.oficina.dao.ProdutoUtilizadoDAO;
import org.example.oficina.model.OrdemServico;
import org.example.oficina.model.ProdutoUtilizado;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.ProdutoUtilizadoValidator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProdutoUtilizadoService {

    private ProdutoUtilizadoValidator produtoUtilizadoValidator = new ProdutoUtilizadoValidator();

    public boolean salvarProdutoUtilizado(int idOrdemServico, ProdutoUtilizado produtoUtilizado) throws SQLException {
        List<String> erros = produtoUtilizadoValidator.validarAtributos(produtoUtilizado);
        OrdemServicoService ordemServicoService = new OrdemServicoService();

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }

        try(Connection conn = DatabaseConnection.getConnection()){
            ProdutoUtilizadoDAO produtoUtilizadoDAO = new ProdutoUtilizadoDAO(conn);
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            Optional<OrdemServico> optionalOrdemServico = ordemServicoDAO.findById(idOrdemServico);

            if(!optionalOrdemServico.isEmpty()){
                produtoUtilizado.setOrdemServico(optionalOrdemServico.get());
                produtoUtilizadoDAO.create(produtoUtilizado);
                ordemServicoService.atualizarValorTotal(idOrdemServico);
                return true;
            } else
                return false;
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public boolean atualizarProdutoUtilizado(ProdutoUtilizado produtoUtilizado) throws SQLException{
        List<String> erros = produtoUtilizadoValidator.validarAtributos(produtoUtilizado);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            ProdutoUtilizadoDAO produtoUtilizadoDAO = new ProdutoUtilizadoDAO(conn);
            return produtoUtilizadoDAO.update(produtoUtilizado);
        }
    }

    public boolean deletarServico(int id) throws SQLException{
        try(Connection conn = DatabaseConnection.getConnection()){
            ProdutoUtilizadoDAO produtoUtilizadoDAO = new ProdutoUtilizadoDAO(conn);
            return produtoUtilizadoDAO.delete(id);
        }
    }

    public Optional<ProdutoUtilizado> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProdutoUtilizadoDAO produtoUtilizadoDAO = new ProdutoUtilizadoDAO(conn);
            return produtoUtilizadoDAO.findById(id);
        }
    }

    public List<ProdutoUtilizado> listarTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProdutoUtilizadoDAO produtoUtilizadoDAO = new ProdutoUtilizadoDAO(conn);
            return produtoUtilizadoDAO.findAll();
        }
    }

}
