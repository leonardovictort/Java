package org.example.oficina.validator;

import org.example.oficina.model.ProdutoUtilizado;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoUtilizadoValidator {

    public List<String> validar(ProdutoUtilizado produto) {
        List<String> erros = new ArrayList<>();

        if (produto.getCategoria() == null) {
            erros.add("Categoria do produto deve ser informada.");
        }

        if (produto.getMarca() == null || produto.getMarca().trim().isEmpty()) {
            erros.add("Marca do produto não pode estar vazia.");
        }

        if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
            erros.add("Descrição do produto não pode estar vazia.");
        }

        if (produto.getCustoUnitario() == null || produto.getCustoUnitario().compareTo(BigDecimal.ZERO) < 0) {
            erros.add("Custo unitário deve ser maior ou igual a zero.");
        }

        if (produto.getPrecoVenda() == null || produto.getPrecoVenda().compareTo(BigDecimal.ZERO) <= 0) {
            erros.add("Preço de venda deve ser maior que zero.");
        }

        if (produto.getQuantidade() <= 0) {
            erros.add("Quantidade deve ser maior que zero.");
        }

        return erros;
    }
}

