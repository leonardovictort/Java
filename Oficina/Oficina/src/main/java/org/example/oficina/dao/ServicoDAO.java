package org.example.oficina.dao;

import org.example.oficina.model.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoDAO {

    private final Connection connection;

    private static final String INSERT_SQL =
            "INSERT INTO servico (descricao, valor, mecanico_id, concluido, observacao, ordem_servico_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE servico SET descricao = ?, valor = ?, mecanico_id = ?, concluido = ?, observacao = ?, ordem_servico_id = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM servico WHERE id = ?";

    private static final String RETRIEVE_BY_ID_SQL =
            "SELECT * FROM servico WHERE id = ?";

    private static final String RETRIEVE_ALL_SQL =
            "SELECT * FROM servico";

    public ServicoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Servico servico) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            preencherStatement(stmt, servico);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir serviço", e);
        }
    }

    public boolean update(Servico servico) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            preencherStatement(stmt, servico);
            stmt.setInt(7, servico.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço", e);
        }
    }

    public boolean delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir serviço", e);
        }
    }

    public Optional<Servico> findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(extrairServico(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço por ID", e);
        }
    }

    public List<Servico> findAll() {
        List<Servico> servicos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                servicos.add(extrairServico(rs));
            }
            return servicos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os serviços", e);
        }
    }

    private void preencherStatement(PreparedStatement stmt, Servico s) throws SQLException {
        stmt.setString(1, s.getDescricao());
        stmt.setBigDecimal(2, s.getValor());
        stmt.setInt(3, s.getMecanico().getId());
        stmt.setBoolean(4, s.isConcluido());
        stmt.setString(5, s.getObservacao());
        stmt.setInt(6, s.getOrdemServico().getId());
    }

    private Servico extrairServico(ResultSet rs) throws SQLException {
        Servico s = new Servico(rs.getString("descricao"), rs.getBigDecimal("valor"));
        s.setId(rs.getInt("id"));
        s.setConcluido(rs.getBoolean("concluido"));
        s.setObservacao(rs.getString("observacao"));
        // Aqui você pode fazer busca do mecanico e ordemServico por ID se necessário
        return s;
    }
}

