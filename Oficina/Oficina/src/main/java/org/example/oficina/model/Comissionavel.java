package org.example.oficina.model;

import java.math.BigDecimal;

public interface Comissionavel {
    BigDecimal calcularComissao(BigDecimal valorServico);
}

