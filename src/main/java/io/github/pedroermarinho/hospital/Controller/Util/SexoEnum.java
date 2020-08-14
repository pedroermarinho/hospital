package io.github.pedroermarinho.hospital.Controller.Util;

public enum SexoEnum {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    final String descricao;
    SexoEnum(String descricao){
        this.descricao =descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
