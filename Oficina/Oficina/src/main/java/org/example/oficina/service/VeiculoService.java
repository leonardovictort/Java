package org.example.oficina.service;

import org.example.oficina.dao.VeiculoDAO;
import org.example.oficina.model.Veiculo;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.VeiculoValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VeiculoService {

    private VeiculoValidator veiculoValidator = new VeiculoValidator();

    public boolean salvarVeiculo(Veiculo veiculo) throws SQLException {
        List<String> erros = veiculoValidator.validarAtributos(veiculo);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            VeiculoDAO veiculoDAO = new VeiculoDAO(conn);
            return veiculoDAO.create(veiculo);
        }
    }

    public boolean atualizarVeiculo(Veiculo veiculo) throws SQLException{
        List<String> erros = veiculoValidator.validarAtributos(veiculo);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            VeiculoDAO veiculoDAO = new VeiculoDAO(conn);
            return veiculoDAO.update(veiculo);
        }
    }

    public boolean deletarVeiculo(int id) throws SQLException{
        try(Connection conn = DatabaseConnection.getConnection()){
            VeiculoDAO veiculoDAO = new VeiculoDAO(conn);
           return veiculoDAO.delete(id);
        }
    }

    public Optional<Veiculo> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            VeiculoDAO veiculoDAO = new VeiculoDAO(conn);
            return veiculoDAO.findById(id);
        }
    }

    public List<Veiculo> listarTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            VeiculoDAO veiculoDAO = new VeiculoDAO(conn);
            return veiculoDAO.findAll();
        }
    }

}
