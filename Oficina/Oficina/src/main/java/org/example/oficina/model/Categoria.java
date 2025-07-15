package org.example.oficina.model;

public enum Categoria {
    BATERIA("Bateria"),
    LUBRIFICANTE("Lubrificante"),
    FILTRO("Filtro"),
    PECA("Peça"),
    FERRAMENTA("Ferramenta"),
    ACESSORIO("Acessório"),
    PNEU("Pneu"),
    CAMERA_DE_AR("Câmera de Ar"),
    RODA("Roda"),
    PARAFUSO("Parafuso"),
    ROLAMENTO("Rolamento"),
    RETENTOR("Retentor"),
    VELA("Vela"),
    CABO("Cabo"),
    LAMPADA("Lâmpada"),
    FUSIVEL("Fusível"),
    SENSOR("Sensor"),
    EMBREAGEM("Embreagem"),
    ESCAPAMENTO("Escapamento"),
    ADITIVO("Aditivo"),
    OLEO_MOTOR("Óleo Motor"),
    OLEO_CAMBIO("Óleo Câmbio"),
    FLUIDO_FREIO("Fluido de Freio"),
    PASTILHA_FREIO("Pastilha de Freio"),
    DISCO_FREIO("Disco de Freio"),
    AMORTECEDOR("Amortecedor"),
    MOLA("Mola"),
    KIT_SUSPENSAO("Kit Suspensão"),
    CORREIA("Correia"),
    TENSOR("Tensor"),
    BOMBA_COMBUSTIVEL("Bomba de Combustível"),
    BOMBA_AGUA("Bomba de Água"),
    JUNTA("Junta"),
    FILTRO_AR("Filtro de Ar"),
    FILTRO_COMBUSTIVEL("Filtro de Combustível"),
    FILTRO_OLEO("Filtro de Óleo"),
    PRODUTO_LIMPEZA("Produto de Limpeza"),
    ESTETICA_AUTOMOTIVA("Estética Automotiva"),
    CONSUMIVEL("Consumível"),
    EQUIPAMENTO_SEGURANCA("Equipamento de Segurança"),
    OUTROS("Outros");

    private final String descricao;

    Categoria(String descricao){
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

