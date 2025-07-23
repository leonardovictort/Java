package org.example.oficina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.oficina.dao.*;
import org.example.oficina.model.*;
import org.example.oficina.service.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HelloApplication /*extends Application */{
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(scene);
        stage.show();
    }*/

    public static void main(String[] args) throws SQLException {
       // launch();

        ClienteService clienteService = new ClienteService();
        VeiculoService veiculoService = new VeiculoService();
        CargoService cargoService = new CargoService();
        FuncionarioService funcionarioService = new FuncionarioService();
        OrdemServicoService ordemServicoService = new OrdemServicoService();
        ProdutoUtilizadoService produtoUtilizadoService = new ProdutoUtilizadoService();
        ServicoService servicoService = new ServicoService();

        Cliente cliente = new Cliente();
        cliente.setNome("Leonardo");cliente.setDocumento("00835831140");cliente.setPessoaJuridica(false);
        cliente.setEmail("Theleonardovictorteodoro@gmail.com");cliente.setTelefone("64993139011");cliente.setEndereco("Morrinhos-GO");
        cliente.setId(3);
        /*
            clienteService.salvarCliente(cliente);
            clienteService.atualizarCliente(cliente);
            clienteService.buscarPorId();
            clienteService.listarTodos();
            clienteService.deletarCliente();
         */

        //clienteService.salvarCliente(cliente);

        //clienteService.atualizarCliente(cliente);

        //Optional<Cliente> clienteX = clienteService.buscarPorId(3);
        //System.out.println(clienteX.toString());

        /*List<Cliente> clientes = clienteService.listarTodos();
        for(Cliente clienteS: clientes){
            System.out.println(clienteS.toString());
        }*/

        //clienteService.deletarCliente(3); necessario deletar o veiculo antes pois existe um vinculo de chaves id;


    }
}