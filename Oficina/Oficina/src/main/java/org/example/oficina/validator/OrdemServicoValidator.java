package org.example.oficina.validator;

import org.example.oficina.model.OrdemServico;

import java.util.ArrayList;
import java.util.List;

public class OrdemServicoValidator {

    public List<String> validarAtributos(OrdemServico ordemServico) {
        List<String> erros = new ArrayList<>();

        if (ordemServico.getCliente() == null) {
            erros.add("Cliente é obrigatório.");
        }

        if (ordemServico.getVeiculo() == null) {
            erros.add("Veículo é obrigatório.");
        }

        if (ordemServico.getEntrada() == null) {
            erros.add("Data de entrada é obrigatória.");
        }

        if (ordemServico.getEntrega() != null && ordemServico.getEntrega().isBefore(ordemServico.getEntrada())) {
            erros.add("Data de entrega não pode ser anterior à data de entrada.");
        }

        if (ordemServico.getStatusOS() == null) {
            erros.add("Status da Ordem de Serviço é obrigatório.");
        }

        return erros;
    }
}

