package org.example.oficina.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Mecanico extends Cargo implements Comissionavel {

    private final double percentualComissao = 0.5;

    public Mecanico(BigDecimal salario) {
        super.setNome("Mecânico");
        super.setDescricao("Responsável por executar serviços mecânicos");
        super.setSalario(salario);
    }

    @Override
    public BigDecimal calcularComissao(BigDecimal valorServico) {
        return valorServico.multiply(new BigDecimal(percentualComissao));
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mecanico mecanico)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(mecanico.getPercentualComissao(), getPercentualComissao()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPercentualComissao());
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "percentualComissao=" + percentualComissao +
                '}';
    }
}
