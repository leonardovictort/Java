package org.example.oficina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.oficina.dao.*;
import org.example.oficina.model.*;
import org.example.oficina.service.OrdemServicoService;

import java.io.IOException;
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

    public static void main(String[] args) {
       // launch();

        String url = "jdbc:postgresql://localhost/Oficina";
        String user = "postgres";
        String password = "123";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            CargoDAO cargoDAO = new CargoDAO(connection);
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(connection);
            VeiculoDAO veiculoDAO = new VeiculoDAO(connection);
            OrdemServicoService os = new OrdemServicoService();

            Optional<Cliente> optionalCliente = clienteDAO.findById(1);
            Cliente clientee = optionalCliente.get();

            Optional<Veiculo> optionalVeiculo = veiculoDAO.findById(1);
            Veiculo veiculoo = optionalVeiculo.get();

            Optional<Funcionario> optionalFuncionario = funcionarioDAO.findById(4);
            Funcionario funcionario1 = optionalFuncionario.get();

            //---------------------------------------------------------------------------------------
            List<Servico> svs = new ArrayList<>();
            Servico servico = new Servico("Troca de Oleo",new BigDecimal(22.50));
            servico.setMecanico(funcionario1);
            //svs.add(servico);

            Servico servico2 = new Servico("Alinhamento e Balanceamento", new BigDecimal("75.00"));
            servico2.setMecanico(funcionario1);
            svs.add(servico2);

            Servico servico3 = new Servico("Revisão dos Freios", new BigDecimal("120.00"));
            servico3.setMecanico(funcionario1);
            svs.add(servico3);

            Servico servico4 = new Servico("Substituição da Correia Dentada", new BigDecimal("250.00"));
            servico4.setMecanico(funcionario1);
            svs.add(servico4);

            Servico servico5 = new Servico("Alinhamento e Balanceamento", new BigDecimal("80.00"));
            servico5.setMecanico(funcionario1);
            svs.add(servico5);

            Servico servico6 = new Servico("Troca de Filtro de Ar", new BigDecimal("45.00"));
            servico6.setMecanico(funcionario1);
            svs.add(servico6);


            Optional<OrdemServico> optionalOrdemServico = ordemServicoDAO.findById(2);
            OrdemServico os2 = optionalOrdemServico.get();
            os.vincularOSeServicos(2,svs);
            //os.salvarOrdemServico(os2);




        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}