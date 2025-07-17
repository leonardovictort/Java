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


    public boolean salvarOrdemServico(OrdemServico ordemServico) throws SQLException {
        List<String> erros = ordemServicoValidator.validarAtributos(ordemServico);

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            return ordemServicoDAO.create(ordemServico);
        }
    }

    public boolean adicionarServicoNaOS(int idOrdemServico, Servico servico) throws SQLException {
        Optional<OrdemServico> optionalOrdemServico = buscarPorId(idOrdemServico);

        if (optionalOrdemServico.isEmpty()) {
            return false; // ordem não existe
        }

        OrdemServico ordemServico = optionalOrdemServico.get();
        servico.setOrdemServico(ordemServico);
        ordemServico.adicionarServico(servico);

        // Persistir no banco
        try (Connection conn = DatabaseConnection.getConnection()) {
            ServicoDAO servicoDAO = new ServicoDAO(conn); // ou injete via construtor
            return servicoDAO.create(servico);
        }
}

    public Optional<OrdemServico> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conn);
            return ordemServicoDAO.findById(id);
        }
    }

}
