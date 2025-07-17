package org.example.oficina.dao;

import org.example.oficina.model.Cliente;
import org.example.oficina.model.TipoCombustivel;
import org.example.oficina.model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoDAO {

    private final Connection connection;

    private static final String INSERT_SQL =
            "INSERT INTO veiculo (placa, marca, modelo, km, cor, ano_fabricacao, tipo_combustivel, chassi, proprietario_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE veiculo SET placa = ?, marca = ?, modelo = ?, km = ?, cor = ?, ano_fabricacao = ?, tipo_combustivel = ?, chassi = ?, proprietario_id = ? " +
                    "WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM veiculo WHERE id = ?";

    private static final String RETRIEVE_BY_ID_SQL =
            "SELECT * FROM veiculo WHERE id = ?";

    private static final String RETRIEVE_ALL_SQL =
            "SELECT * FROM veiculo";

    public VeiculoDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public boolean create(Veiculo veiculo) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            preencherStatement(stmt, veiculo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir veículo", e);
        }
    }

    // UPDATE
    public boolean update(Veiculo veiculo) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            preencherStatement(stmt, veiculo);
            stmt.setInt(10, veiculo.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo", e);
        }
    }

    // DELETE
    public boolean delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir veículo", e);
        }
    }

    // READ BY ID
    public Optional<Veiculo> findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(extrairVeiculo(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículo por ID", e);
        }
    }

    // READ ALL
    public List<Veiculo> findAll() {
        List<Veiculo> veiculos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                veiculos.add(extrairVeiculo(rs));
            }
            return veiculos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículos", e);
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    private void preencherStatement(PreparedStatement stmt, Veiculo veiculo) throws SQLException {
        stmt.setString(1, veiculo.getPlaca());
        stmt.setString(2, veiculo.getMarca());
        stmt.setString(3, veiculo.getModelo());
        stmt.setString(4, veiculo.getKm());
        stmt.setString(5, veiculo.getCor());
        stmt.setInt(6, veiculo.getAnoFabricacao());
        stmt.setString(7, veiculo.getTipoCombustivel().name());
        stmt.setString(8, veiculo.getChassi());
        stmt.setInt(9, veiculo.getProprietario().getId());
    }

    private Veiculo extrairVeiculo(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(rs.getInt("id"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setMarca(rs.getString("marca"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setKm(rs.getString("km"));
        veiculo.setCor(rs.getString("cor"));
        veiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
        veiculo.setTipoCombustivel(TipoCombustivel.valueOf(rs.getString("tipo_combustivel")));
        veiculo.setChassi(rs.getString("chassi"));

        // Aqui você precisará buscar o Cliente do banco, se quiser carregar ele completo:
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        clienteDAO.findById(rs.getInt("proprietario_id")).ifPresent(veiculo::setProprietario);

        return veiculo;
    }
}