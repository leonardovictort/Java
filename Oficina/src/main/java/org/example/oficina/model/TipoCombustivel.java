package org.example.oficina.model;

public enum TipoCombustivel {
    ETANOL("Etanol"),
    GASOLINA("Gasolina"),
    FLEX("Flex"),
    DIESEL_S500("Diesel S-500"),
    DIESEL_S10("Diesel S-10");

    private final String descricao;

    TipoCombustivel(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
