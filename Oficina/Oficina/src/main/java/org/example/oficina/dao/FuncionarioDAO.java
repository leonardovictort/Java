package org.example.oficina.dao;

import org.example.oficina.model.Cargo;
import org.example.oficina.model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioDAO {

    private final Connection connection;

    private static final String INSERT_SQL =
            "INSERT INTO funcionario (nome, cpf, cargo_id) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE funcionario SET nome = ?, cpf = ?, cargo_id = ? WHERE id = ?";
    private static final String DELETE_SQL =
            "DELETE FROM funcionario WHERE id = ?";
    private static final String RETRIEVE_BY_ID_SQL =
            "SELECT f.*, c.nome AS cargo_nome, c.descricao, c.salario FROM funcionario f " +
                    "JOIN cargo c ON f.cargo_id = c.id WHERE f.id = ?";
    private static final String RETRIEVE_ALL_SQL =
            "SELECT f.*, c.nome AS cargo_nome, c.descricao, c.salario FROM funcionario f " +
                    "JOIN cargo c ON f.cargo_id = c.id";

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Funcionario funcionario) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            preencherStatement(stmt, funcionario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir funcionário", e);
        }
    }

    public boolean update(Funcionario funcionario) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            preencherStatement(stmt, funcionario);
            stmt.setInt(4, funcionario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário", e);
        }
    }

    public boolean delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar funcionário", e);
        }
    }

    public Optional<Funcionario> findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return Optional.of(extrairFuncionario(rs));
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionário por ID", e);
        }
    }

    public List<Funcionario> findAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                funcionarios.add(extrairFuncionario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os funcionários", e);
        }
        return funcionarios;
    }

    private void preencherStatement(PreparedStatement stmt, Funcionario funcionario) throws SQLException {
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getCpf());
        stmt.setInt(3, funcionario.getCargo().getId());
    }

    private Funcionario extrairFuncionario(ResultSet rs) throws SQLException {
        Cargo cargo = new Cargo() {}; // classe anônima para instanciar Cargo abstrato
        cargo.setId(rs.getInt("cargo_id"));
        cargo.setNome(rs.getString("cargo_nome"));
        cargo.setDescricao(rs.getString("descricao"));
        cargo.setSalario(rs.getBigDecimal("salario"));

        Funcionario funcionario = new Funcionario();
        funcionario.setId(rs.getInt("id"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setCargo(cargo);

        return funcionario;
    }
}