package org.example.oficina.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.oficina.model.Cliente;
import org.example.oficina.service.ClienteService;

import java.sql.SQLException;

public class ClienteController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField documentoField;

    @FXML
    private CheckBox pessoaJuridicaCheck;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField enderecoField;

    private final ClienteService clienteService = new ClienteService();

    @FXML
    private void onSalvarButtonClick() {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome(nomeField.getText());
            cliente.setDocumento(documentoField.getText());
            cliente.setPessoaJuridica(pessoaJuridicaCheck.isSelected());
            cliente.setTelefone(telefoneField.getText());
            cliente.setEmail(emailField.getText());
            cliente.setEndereco(enderecoField.getText());

            clienteService.salvarCliente(cliente);
            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cliente salvo com sucesso!");

            limparCampos();

        } catch (IllegalArgumentException e) {
            exibirAlerta(Alert.AlertType.WARNING, "Validação", e.getMessage());
        } catch (SQLException e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao salvar cliente no banco: " + e.getMessage());
        }
    }

    @FXML
    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void limparCampos() {
        nomeField.clear();
        documentoField.clear();
        pessoaJuridicaCheck.setSelected(false);
        telefoneField.clear();
        emailField.clear();
        enderecoField.clear();
    }
}
