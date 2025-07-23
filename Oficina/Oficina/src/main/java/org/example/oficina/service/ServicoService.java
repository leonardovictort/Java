package org.example.oficina.service;

import org.example.oficina.dao.OrdemServicoDAO;
import org.example.oficina.dao.ServicoDAO;
import org.example.oficina.model.OrdemServico;
import org.example.oficina.model.Servico;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.ServicoValidator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ServicoService {

    private ServicoValidator servicoValidator = new ServicoValidator();

    public boolean salvarServico(int idOrdemServico, Servico servico) throws SQLException {
        List<String> erros = servicoValidator.validarAtributos(servico);
        OrdemServicoService ordemServicoService = new OrdemServicoService();
        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            ServicoDAO servicoDAO = new ServicoDAO(conn);
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            Optional<OrdemServico> optionalOrdemServico = ordemServicoDAO.findById(idOrdemServico);

            if(!optionalOrdemServico.isEmpty()){
                servico.setOrdemServico(optionalOrdemServico.get());
                servicoDAO.create(servico);
                ordemServicoService.atualizarValorTotal(idOrdemServico);
                return true;
            } else
                return false;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean atualizarServico(Servico servico) throws SQLException{
        List<String> erros = servicoValidator.validarAtributos(servico);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            ServicoDAO servicoDAO = new ServicoDAO(conn);
            return servicoDAO.update(servico);
        }
    }

    public boolean deletarServico(int id) throws SQLException{
        try(Connection conn = DatabaseConnection.getConnection()){
            ServicoDAO servicoDAO = new ServicoDAO(conn);
            return servicoDAO.delete(id);
        }
    }

    public Optional<Servico> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ServicoDAO servicoDAO = new ServicoDAO(conn);
            return servicoDAO.findById(id);
        }
    }

    public List<Servico> listarTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ServicoDAO servicoDAO = new ServicoDAO(conn);
            return servicoDAO.findAll();
        }
    }
}
