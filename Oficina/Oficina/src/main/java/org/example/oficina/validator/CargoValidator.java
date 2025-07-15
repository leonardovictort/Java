package org.example.oficina.validator;

import org.example.oficina.model.Cargo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CargoValidator {

    public List<String> validarAtributos(Cargo cargo) {
        List<String> erros = new ArrayList<>();

        if (cargo.getNome() == null || cargo.getNome().isBlank()) {
            erros.add("O nome do cargo é obrigatório.");
        }

        if (cargo.getSalario() == null || cargo.getSalario().compareTo(BigDecimal.ZERO) < 0) {
            erros.add("O salário deve ser informado e maior ou igual a zero.");
        }

        if (cargo.getDescricao() == null || cargo.getDescricao().isBlank()) {
            erros.add("A descrição do cargo é obrigatória.");
        }

        return erros;
    }
}

