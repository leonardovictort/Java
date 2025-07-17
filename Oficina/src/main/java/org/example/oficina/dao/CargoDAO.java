package org.example.oficina.dao;

import org.example.oficina.model.Cargo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CargoDAO {

    private final Connection connection;

    private static final String INSERT_SQL = "INSERT INTO cargo (nome, descricao, salario) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE cargo SET nome = ?, descricao = ?, salario = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM cargo WHERE id = ?";
    private static final String RETRIEVE_BY_ID_SQL = "SELECT * FROM cargo WHERE id = ?";
    private static final String RETRIEVE_ALL_SQL = "SELECT * FROM cargo";

    public CargoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Cargo cargo) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            preencherStatement(stmt, cargo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cargo", e);
        }
    }

    public boolean update(Cargo cargo) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            preencherStatement(stmt, cargo);
            stmt.setInt(4, cargo.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cargo", e);
        }
    }

    public boolean delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cargo", e);
        }
    }

    public Optional<Cargo> findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return Optional.of(extrairCargo(rs));
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cargo por ID", e);
        }
    }

    public List<Cargo> findAll() {
        List<Cargo> cargos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cargos.add(extrairCargo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os cargos", e);
        }
        return cargos;
    }

    private void preencherStatement(PreparedStatement stmt, Cargo cargo) throws SQLException {
        stmt.setString(1, cargo.getNome());
        stmt.setString(2, cargo.getDescricao());
        stmt.setBigDecimal(3, cargo.getSalario());
    }

    private Cargo extrairCargo(ResultSet rs) throws SQLException {
        Cargo cargo = new Cargo() {}; // classe anônima para instanciar Cargo abstrato
        cargo.setId(rs.getInt("id"));
        cargo.setNome(rs.getString("nome"));
        cargo.setDescricao(rs.getString("descricao"));
        cargo.setSalario(rs.getBigDecimal("salario"));
        return cargo;
    }
}
