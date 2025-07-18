package org.example.oficina.service;

import org.example.oficina.dao.ClienteDAO;
import org.example.oficina.dao.OrdemServicoDAO;
import org.example.oficina.dao.ServicoDAO;
import org.example.oficina.model.*;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.ClienteValidator;
import org.example.oficina.validator.OrdemServicoValidator;
import org.example.oficina.validator.ProdutoUtilizadoValidator;
import org.example.oficina.validator.ServicoValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrdemServicoService {

    private final OrdemServicoValidator ordemServicoValidator = new OrdemServicoValidator();


    public boolean vincularOSeServicos(int idOrdemServico, List<Servico> servicos) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            Optional<OrdemServico> osOptional = ordemServicoDAO.findById(idOrdemServico);
            //ServicoDAO servicoDAO = new ServicoDAO(conn);

            if (!osOptional.isEmpty()) {
                OrdemServico ordemServico = osOptional.get();
                salvarServicos(ordemServico, servicos);
                return true;
            } else
                return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean salvarServicos(OrdemServico os, List<Servico> servicos) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ServicoDAO servicoDAO = new ServicoDAO(conn);
            for (Servico servico : servicos) {
                servico.setOrdemServico(os);
                servicoDAO.create(servico);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<OrdemServico> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            return ordemServicoDAO.findById(id);
        }
    }

}
