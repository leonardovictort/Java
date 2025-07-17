package org.example.oficina.dao;

import org.example.oficina.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    private final Connection connection;

    private static final String INSERT_SQL =
            "INSERT INTO cliente (nome, documento, pessoa_juridica, telefone, email, endereco) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE cliente SET nome = ?, documento = ?, pessoa_juridica = ?, telefone = ?, email = ?, endereco = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM cliente WHERE id = ?";

    private static final String RETRIEVE_BY_ID_SQL =
            "SELECT * FROM cliente WHERE id = ?";

    private static final String RETRIEVE_ALL_SQL =
            "SELECT * FROM cliente";

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public boolean create(Cliente cliente) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            preencherStatement(stmt, cliente);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente", e);
        }
    }

    // UPDATE
    public boolean update(Cliente cliente) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            preencherStatement(stmt, cliente);
            stmt.setInt(7, cliente.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    // DELETE
    public boolean delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente", e);
        }
    }

    // READ BY ID
    public Optional<Cliente> findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(extrairCliente(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID", e);
        }
    }

    // READ ALL
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(extrairCliente(rs));
            }

            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os clientes", e);
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    private void preencherStatement(PreparedStatement stmt, Cliente cliente) throws SQLException {
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getDocumento());
        stmt.setBoolean(3, cliente.isPessoaJuridica());
        stmt.setString(4, cliente.getTelefone());
        stmt.setString(5, cliente.getEmail());
        stmt.setString(6, cliente.getEndereco());
    }

    private Cliente extrairCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setDocumento(rs.getString("documento"));
        cliente.setPessoaJuridica(rs.getBoolean("pessoa_juridica"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        cliente.setEndereco(rs.getString("endereco"));
        return cliente;
    }
}
