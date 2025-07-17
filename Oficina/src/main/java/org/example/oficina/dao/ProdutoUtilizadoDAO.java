package org.example.oficina.dao;

import org.example.oficina.model.Categoria;
import org.example.oficina.model.ProdutoUtilizado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoUtilizadoDAO {

    private final Connection connection;

    private static final String INSERT_SQL =
            "INSERT INTO produto_utilizado (categoria, marca, descricao, custo_unitario, preco_venda, quantidade, valor_total, ordem_servico_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE produto_utilizado SET categoria = ?, marca = ?, descricao = ?, custo_unitario = ?, preco_venda = ?, quantidade = ?, valor_total = ?, ordem_servico_id = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM produto_utilizado WHERE id = ?";

    private static final String RETRIEVE_BY_ID_SQL =
            "SELECT * FROM produto_utilizado WHERE id = ?";

    private static final String RETRIEVE_ALL_SQL =
            "SELECT * FROM produto_utilizado";

    public ProdutoUtilizadoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(ProdutoUtilizado p) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            preencherStatement(stmt, p);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto utilizado", e);
        }
    }

    public boolean update(ProdutoUtilizado p) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            preencherStatement(stmt, p);
            stmt.setInt(9, p.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto utilizado", e);
        }
    }

    public boolean delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto utilizado", e);
        }
    }

    public Optional<ProdutoUtilizado> findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(extrairProduto(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto utilizado por ID", e);
        }
    }

    public List<ProdutoUtilizado> findAll() {
        List<ProdutoUtilizado> produtos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(RETRIEVE_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produtos.add(extrairProduto(rs));
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os produtos utilizados", e);
        }
    }

    private void preencherStatement(PreparedStatement stmt, ProdutoUtilizado p) throws SQLException {
        stmt.setString(1, p.getCategoria().name());
        stmt.setString(2, p.getMarca());
        stmt.setString(3, p.getDescricao());
        stmt.setBigDecimal(4, p.getCustoUnitario());
        stmt.setBigDecimal(5, p.getPrecoVenda());
        stmt.setInt(6, p.getQuantidade());
        stmt.setBigDecimal(7, p.getValorTotal());
        stmt.setInt(8, p.getOrdemServico().getId());
    }

    private ProdutoUtilizado extrairProduto(ResultSet rs) throws SQLException {
        ProdutoUtilizado p = new ProdutoUtilizado(
                Categoria.valueOf(rs.getString("categoria")),
                rs.getString("marca"),
                rs.getString("descricao"),
                rs.getBigDecimal("custo_unitario"),
                rs.getBigDecimal("preco_venda"),
                rs.getInt("quantidade")
        );
        p.setId(rs.getInt("id"));
        p.setValorTotal(rs.getBigDecimal("valor_total"));
        // Você pode atribuir a ordem de serviço aqui se necessário
        return p;
    }
}
