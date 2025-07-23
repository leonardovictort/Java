package org.example.oficina.dao;
import org.example.oficina.model.OrdemServico;
import org.example.oficina.model.StatusOS;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdemServicoDAO {

    private final Connection connection;

    private static final String INSERT_SQL =
            "INSERT INTO ordem_servico (entrada, entrega, status_os, valor_total, cliente_id, veiculo_id, observacoes) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE ordem_servico SET entrada = ?, entrega = ?, status_os = ?, valor_total = ?, cliente_id = ?, veiculo_id = ?, observacoes = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM ordem_servico WHERE id = ?";

    private static final String RETRIEVE_BY_ID_SQL =
            "SELECT * FROM ordem_servico WHERE id = ?";

    private static final String RETRIEVE_ALL_SQL =
            "SELECT * FROM ordem_servico";

    public OrdemServicoDAO(Connection connection) {
        this.connection = connection;
    }


    public boolean create(OrdemServico os) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            preencherStatement(stmt, os);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir ordem de serviço", e);
        }
    }

    public boolean update(OrdemServico os) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            preencherStatement(stmt, os);
            stmt.setInt(8, os.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ordem de serviço", e);
        }
    }

    public boolean delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar ordem de serviço", e);
        }
    }

    public Optional<OrdemServico> findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(extrairOrdemServico(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ordem de serviço por ID", e);
        }
    }

    public List<OrdemServico> findAll() {
        List<OrdemServico> ordens = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ordens.add(extrairOrdemServico(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as ordens de serviço", e);
        }
        return ordens;
    }

    private void preencherStatement(PreparedStatement stmt, OrdemServico os) throws SQLException {
        stmt.setDate(1, os.getEntrada() != null ? Date.valueOf(os.getEntrada()) : null);
        stmt.setDate(2, os.getEntrega() != null ? Date.valueOf(os.getEntrega()) : null);
        stmt.setString(3, os.getStatusOS() != null ? os.getStatusOS().name() : null);

        stmt.setBigDecimal(4, os.getValorTotal());
        stmt.setInt(5, os.getCliente().getId());
        stmt.setInt(6, os.getVeiculo().getId());
        stmt.setString(7, os.getObservacoes());
    }

    private OrdemServico extrairOrdemServico(ResultSet rs) throws SQLException {
        OrdemServico os = new OrdemServico();

        os.setId(rs.getInt("id"));

        Date entradaDate = rs.getDate("entrada");
        if (entradaDate != null) {
            os.setEntrada(entradaDate.toLocalDate());
        }

        Date entregaDate = rs.getDate("entrega");
        if (entregaDate != null) {
            os.setEntrega(entregaDate.toLocalDate());
        }

        String statusStr = rs.getString("status_os");
        if (statusStr != null) {
            os.setStatusOS(StatusOS.valueOf(statusStr));
        }

        os.setValorTotal(rs.getBigDecimal("valor_total"));

        ClienteDAO clienteDAO = new ClienteDAO(connection);
        clienteDAO.findById(rs.getInt("cliente_id")).ifPresent(os::setCliente);

        VeiculoDAO veiculoDAO = new VeiculoDAO(connection);
        veiculoDAO.findById(rs.getInt("veiculo_id")).ifPresent(os::setVeiculo);

        os.setObservacoes(rs.getString("observacoes"));

        // Inicializa listas vazias para evitar NullPointerException
        os.setServicos(new ArrayList<>());
        os.setProdutosUtilizados(new ArrayList<>());

        return os;
    }
}
