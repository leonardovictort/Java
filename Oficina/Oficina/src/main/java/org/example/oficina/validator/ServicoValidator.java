package org.example.oficina.validator;

import org.example.oficina.model.Servico;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServicoValidator {

    public List<String> validarAtributos(Servico servico) {
        List<String> erros = new ArrayList<>();

        if (servico.getDescricao() == null || servico.getDescricao().trim().isEmpty()) {
            erros.add("A descrição do serviço não pode estar vazia.");
        }

        if (servico.getValor() == null || servico.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            erros.add("O valor do serviço deve ser maior que zero.");
        }

        if (servico.getMecanico() == null) {
            erros.add("O mecânico responsável deve ser informado.");
        }

        return erros;
    }
}

