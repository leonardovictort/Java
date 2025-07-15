package org.example.oficina.validator;

import org.example.oficina.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioValidator {

    private final CargoValidator cargoValidator = new CargoValidator();

    public List<String> validarAtributos(Funcionario funcionario) {
        List<String> erros = new ArrayList<>();

        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            erros.add("O nome do funcionário é obrigatório.");
        }

        if (funcionario.getCpf() == null || !funcionario.getCpf().matches("\\d{11}")) {
            erros.add("O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        if (funcionario.getCargo() == null) {
            erros.add("O cargo do funcionário é obrigatório.");
        } else {
            erros.addAll(cargoValidator.validarAtributos(funcionario.getCargo()));
        }

        return erros;
    }
}

