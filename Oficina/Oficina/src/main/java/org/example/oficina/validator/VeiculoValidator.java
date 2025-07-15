package org.example.oficina.validator;

import org.example.oficina.model.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoValidator {

    public List<String> validarAtributos(Veiculo veiculo) {
        List<String> erros = new ArrayList<>();

        if (veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()) {
            erros.add("A placa do veículo não pode estar vazia.");
        } else {
            String placa = veiculo.getPlaca().toUpperCase().trim();

            boolean formatoAntigo = placa.matches("^[A-Z]{3}-\\d{4}$");
            boolean formatoMercosul = placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$");

            if (!formatoAntigo && !formatoMercosul) {
                erros.add("Formato da placa inválido. Use o formato AAA-1234 ou ABC1D23.");
            }
        }

        if (veiculo.getMarca() == null || veiculo.getMarca().isBlank()) {
            erros.add("A marca do veículo é obrigatória.");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().isBlank()) {
            erros.add("O modelo do veículo é obrigatório.");
        }

        if (veiculo.getKm() == null || veiculo.getKm().isBlank()) {
            erros.add("A quilometragem do veículo é obrigatória.");
        }

        if (veiculo.getCor() == null || veiculo.getCor().isBlank()) {
            erros.add("A cor do veículo é obrigatória.");
        }

        if (veiculo.getAnoFabricacao() <= 1900 || veiculo.getAnoFabricacao() > java.time.LocalDate.now().getYear() + 1) {
            erros.add("Ano de fabricação inválido.");
        }

        if (veiculo.getTipoCombustivel() == null) {
            erros.add("O tipo de combustível é obrigatório.");
        }

        if (veiculo.getChassi() == null || veiculo.getChassi().isBlank()) {
            erros.add("O número do chassi é obrigatório.");
        }

        if (veiculo.getProprietario() == null) {
            erros.add("O veículo deve estar vinculado a um cliente.");
        }

        return erros;
    }
}
