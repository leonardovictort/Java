package org.example.oficina.validator;

import org.example.oficina.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteValidator {

    public List<String> validarAtributos(Cliente cliente) {
        List<String> erros = new ArrayList<>();

        // Nome
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            erros.add("O nome não pode estar vazio.");
        }

        // Documento
        if (cliente.getDocumento() == null || cliente.getDocumento().trim().isEmpty()) {
            erros.add("O documento não pode estar vazio.");
        } else {
            if (cliente.isPessoaJuridica()) {
                if (!isCnpjValido(cliente.getDocumento())) {
                    erros.add("CNPJ inválido.");
                }
            } else {
                if (!isCpfValido(cliente.getDocumento())) {
                    erros.add("CPF inválido.");
                }
            }
        }

        // Pessoa Jurídica x Documento
        if (cliente.isPessoaJuridica() && cliente.getDocumento().length() != 14) {
            erros.add("Pessoa jurídica deve ter documento com 14 dígitos.");
        } else if (!cliente.isPessoaJuridica() && cliente.getDocumento().length() != 11) {
            erros.add("Pessoa física deve ter documento com 11 dígitos.");
        }

        // Telefone (mínimo de 10 dígitos)
        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            erros.add("O telefone não pode estar vazio.");
        } else if (!cliente.getTelefone().matches("\\d{10,11}")) {
            erros.add("O telefone deve conter 10 ou 11 dígitos numéricos.");
        }

        // Email
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            erros.add("O email não pode estar vazio.");
        } else if (!cliente.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
            erros.add("O email está em formato inválido.");
        }

        // Endereço
        if (cliente.getEndereco() == null || cliente.getEndereco().trim().isEmpty()) {
            erros.add("O endereço não pode estar vazio.");
        }

        return erros;
    }

    private boolean isCpfValido(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}") || cpf.chars().distinct().count() == 1) return false;

        int soma1 = 0, soma2 = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma1 += digito * (10 - i);
            soma2 += digito * (11 - i);
        }

        int dv1 = 11 - (soma1 % 11);
        dv1 = (dv1 >= 10) ? 0 : dv1;
        soma2 += dv1 * 2;

        int dv2 = 11 - (soma2 % 11);
        dv2 = (dv2 >= 10) ? 0 : dv2;

        return dv1 == Character.getNumericValue(cpf.charAt(9)) &&
                dv2 == Character.getNumericValue(cpf.charAt(10));
    }

    private boolean isCnpjValido(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}") || cnpj.chars().distinct().count() == 1) return false;

        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma1 = 0, soma2 = 0;

        for (int i = 0; i < 12; i++) {
            int digito = Character.getNumericValue(cnpj.charAt(i));
            soma1 += digito * peso1[i];
            soma2 += digito * peso2[i];
        }

        int dv1 = soma1 % 11;
        dv1 = (dv1 < 2) ? 0 : 11 - dv1;
        soma2 += dv1 * peso2[12];

        int dv2 = soma2 % 11;
        dv2 = (dv2 < 2) ? 0 : 11 - dv2;

        return dv1 == Character.getNumericValue(cnpj.charAt(12)) &&
                dv2 == Character.getNumericValue(cnpj.charAt(13));
    }

}
