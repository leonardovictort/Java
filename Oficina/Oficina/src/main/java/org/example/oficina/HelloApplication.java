package org.example.oficina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.oficina.model.*;

import java.io.IOException;
import java.math.BigDecimal;

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

        ordemServico.getServicos().add(new Servico("Troca de Oleo",new BigDecimal(22.50)));
        ordemServico.getServicos().add(new Servico("Concerto de Farol",new BigDecimal(12.50)));
        ordemServico.getProdutosUtilizados().add(new ProdutoUtilizado(Categoria.OLEO_MOTOR,"Mobil","15w40",new BigDecimal(32.50),new BigDecimal(50.00),4));
        ordemServico.getProdutosUtilizados().add(new ProdutoUtilizado(Categoria.FILTRO_OLEO,"Tecfil","NP-7715",new BigDecimal(13.35),new BigDecimal(35.00),1));


        ordemServico.getServicos().get(1).setMecanico(funcionario);

        System.out.println(ordemServico.calcularValorTotal());

        Cargo cargoTest = ordemServico.getServicos().get(1).getMecanico().getCargo();
        BigDecimal comissao = ((Comissionavel) cargo).calcularComissao(ordemServico.getServicos().get(1).getValor());
        System.out.println("Cliente: "+ordemServico.getCliente().getNome()+" Placa Veiculo: "+ordemServico.getVeiculo().getPlaca()+" Data Entrada "+ordemServico.getEntrada());
        System.out.println("Serviço: "+ordemServico.getServicos().get(1).getDescricao() +" - Valor Serviço:  R$"+ ordemServico.getServicos().get(1).getValor());
        System.out.println("Mecanico: "+ordemServico.getServicos().get(1).getMecanico().getNome()+" - Valor Comissao:  R$"+comissao);


    }
}