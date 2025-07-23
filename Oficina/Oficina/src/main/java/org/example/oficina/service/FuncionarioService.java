package org.example.oficina.service;

import org.example.oficina.dao.FuncionarioDAO;
import org.example.oficina.model.Funcionario;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.FuncionarioValidator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FuncionarioService {

    private final FuncionarioValidator funcionarioValidator = new FuncionarioValidator();

    public boolean salvarFuncionario(Funcionario funcionario) throws SQLException {
        List<String> erros = funcionarioValidator.validarAtributos(funcionario);

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
            return funcionarioDAO.create(funcionario);
        }
    }

    public boolean atualizarFuncionario(Funcionario funcionario) throws SQLException {
        List<String> erros = funcionarioValidator.validarAtributos(funcionario);

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
            return funcionarioDAO.update(funcionario);
        }
    }

    public boolean deletarFuncionario(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
            return funcionarioDAO.delete(id);
        }
    }

    public Optional<Funcionario> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
            return funcionarioDAO.findById(id);
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
            return funcionarioDAO.findAll();
        }
    }

}
