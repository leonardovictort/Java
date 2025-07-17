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

        Cliente cliente = new Cliente("Leonardo","05163142119",false,"64993139005","dasdsa@gmail.com","rua abc 1530 Coabe Sinop Mato Grosso");
        Veiculo veiculo = new Veiculo("NVX-7525","Honda","Titan 150","87351","Vermelha",2010, TipoCombustivel.FLEX,"1234657",cliente);
        Mecanico cargo = new Mecanico(new BigDecimal("3500.00"));
        Funcionario funcionario = new Funcionario("Leonardo Victor Teodoro","05163142119",cargo);

        OrdemServico ordemServico = new OrdemServico(cliente,veiculo);

        //ordemServico.getServicos().add(new Servico("Troca de Oleo",new BigDecimal(22.50)));
        //ordemServico.getServicos().add(new Servico("Concerto de Farol",new BigDecimal(12.50)));
        //ordemServico.getProdutosUtilizados().add(new ProdutoUtilizado(Categoria.OLEO_MOTOR,"Mobil","15w40",new BigDecimal(32.50),new BigDecimal(50.00),4));
        //ordemServico.getProdutosUtilizados().add(new ProdutoUtilizado(Categoria.FILTRO_OLEO,"Tecfil","NP-7715",new BigDecimal(13.35),new BigDecimal(35.00),1));


        //ordemServico.getServicos().get(1).setMecanico(funcionario);

        System.out.println(ordemServico.calcularValorTotal());

        //BigDecimal comissao = ((Comissionavel) cargo).calcularComissao(ordemServico.getServicos().get(1).getValor());
        //System.out.println("Cliente: "+ordemServico.getCliente().getNome()+" Placa Veiculo: "+ordemServico.getVeiculo().getPlaca()+" Data Entrada "+ordemServico.getEntrada());
        //System.out.println("Serviço: "+ordemServico.getServicos().get(1).getDescricao() +" - Valor Serviço:  R$"+ ordemServico.getServicos().get(1).getValor());
        //System.out.println("Mecanico: "+ordemServico.getServicos().get(1).getMecanico().getNome()+" - Valor Comissao:  R$"+comissao);

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

            //clienteDAO.create(cliente);


            //Optional<Cargo> optionalCargo = cargoDAO.findById(1);
            //Cargo cargo2 = optionalCargo.get();

            //cargoDAO.create(cargo);
            //funcionario.setCargo(cargo2);
            //funcionarioDAO.create(funcionario);

            Optional<Cliente> optionalCliente = clienteDAO.findById(1);
            Cliente clientee = optionalCliente.get();
            //veiculo.setProprietario(clientee);
            //veiculoDAO.create(veiculo);

            Optional<Veiculo> optionalVeiculo = veiculoDAO.findById(1);
            Veiculo veiculoo = optionalVeiculo.get();

            ordemServico.setCliente(clientee);
            ordemServico.setVeiculo(veiculoo);

            //ordemServicoDAO.create(ordemServico);
            OrdemServicoService os = new OrdemServicoService();

            Optional<Funcionario> optionalFuncionario = funcionarioDAO.findById(4);
            Funcionario funcionario1 = optionalFuncionario.get();
            Servico servico = new Servico("Troca de Oleo",new BigDecimal(22.50));
            servico.setMecanico(funcionario1);
            os.adicionarServicoNaOS(2,servico);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}