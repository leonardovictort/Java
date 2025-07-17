package org.example.oficina.service;

import org.example.oficina.dao.ClienteDAO;
import org.example.oficina.model.Cliente;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.ClienteValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final ClienteValidator clienteValidator = new ClienteValidator();

    public boolean salvarCliente(Cliente cliente) throws SQLException {
        List<String> erros = clienteValidator.validarAtributos(cliente);

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            return clienteDAO.create(cliente);
        }
    }

    public boolean atualizarCliente(Cliente cliente) throws SQLException {
        List<String> erros = clienteValidator.validarAtributos(cliente);

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            return clienteDAO.update(cliente);
        }
    }

    public boolean deletarCliente(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            return clienteDAO.delete(id);
        }
    }

    public Optional<Cliente> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            return clienteDAO.findById(id);
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            return clienteDAO.findAll();
        }
    }
}

