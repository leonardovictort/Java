package org.example.oficina.service;

import org.example.oficina.dao.CargoDAO;
import org.example.oficina.model.Cargo;
import org.example.oficina.util.DatabaseConnection;
import org.example.oficina.validator.CargoValidator;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CargoService {

    private CargoValidator cargoValidator = new CargoValidator();

    public boolean salvarCargo(Cargo cargo) throws SQLException {
        List<String> erros = cargoValidator.validarAtributos(cargo);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            CargoDAO cargoDAO = new CargoDAO(conn);
            return cargoDAO.create(cargo);
        }
    }

    public boolean atualizarCargo(Cargo cargo) throws SQLException{
        List<String> erros = cargoValidator.validarAtributos(cargo);

        if(!erros.isEmpty()){
            throw new IllegalArgumentException("Erro(s) de validação: " + String.join(".\n ", erros));
        }
        try(Connection conn = DatabaseConnection.getConnection()){
            CargoDAO cargoDAO = new CargoDAO(conn);
            return cargoDAO.update(cargo);
        }
    }

    public boolean deletarCargo(int id) throws SQLException{
        try(Connection conn = DatabaseConnection.getConnection()){
            CargoDAO cargoDAO = new CargoDAO(conn);
            return cargoDAO.delete(id);
        }
    }

    public Optional<Cargo> buscarPorId(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CargoDAO cargoDAO = new CargoDAO(conn);
            return cargoDAO.findById(id);
        }
    }

    public List<Cargo> listarTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CargoDAO cargoDAO = new CargoDAO(conn);
            return cargoDAO.findAll();
        }
    }
}
