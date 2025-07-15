package org.example.oficina.validator;

import org.example.oficina.model.Mecanico;

import java.util.ArrayList;
import java.util.List;

public class MecanicoValidator {

    private final CargoValidator cargoValidator = new CargoValidator();

    public List<String> validarAtributos(Mecanico mecanico) {
        List<String> erros = new ArrayList<>(cargoValidator.validarAtributos(mecanico));

        if (mecanico.getPercentualComissao() <= 0 || mecanico.getPercentualComissao() > 1) {
            erros.add("O percentual de comissão do mecânico deve estar entre 0 e 1.");
        }

        return erros;
    }
}

