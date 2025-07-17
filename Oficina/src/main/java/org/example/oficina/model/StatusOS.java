package org.example.oficina.model;

public enum StatusOS {
    ABERTA("Aberta"),
    EM_ANDAMENTO("Em andamento"),
    AGUARDANDO_PECAS("Aguardando peças"),
    AGUARDANDO_APROVACAO("Aguardando aprovação do cliente"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada"),
    ENTREGUE("Entregue");

    private final String descricao;

    StatusOS(String descricao) {
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
