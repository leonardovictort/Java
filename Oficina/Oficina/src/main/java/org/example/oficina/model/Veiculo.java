package org.example.oficina.model;

import java.util.Objects;

public class Veiculo {

    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String km;
    private String cor;
    private int anoFabricacao;
    private TipoCombustivel tipoCombustivel;
    private String chassi;
    private Cliente proprietario;

    public Veiculo() {

    }

    public Veiculo(String placa, String marca, String modelo, String km, String cor, int anoFabricacao, TipoCombustivel tipoCombustivel, String chassi, Cliente proprietario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.cor = cor;
        this.anoFabricacao = anoFabricacao;
        this.tipoCombustivel = tipoCombustivel;
        this.chassi = chassi;
        this.proprietario = proprietario;
    }

    public Veiculo(int id, String placa, String marca, String modelo, String km, String cor, int anoFabricacao, TipoCombustivel tipoCombustivel, String chassi, Cliente proprietario) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.cor = cor;
        this.anoFabricacao = anoFabricacao;
        this.tipoCombustivel = tipoCombustivel;
        this.chassi = chassi;
        this.proprietario = proprietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo veiculo)) return false;
        return getId() == veiculo.getId() && getAnoFabricacao() == veiculo.getAnoFabricacao() && Objects.equals(getPlaca(), veiculo.getPlaca()) && Objects.equals(getMarca(), veiculo.getMarca()) && Objects.equals(getModelo(), veiculo.getModelo()) && Objects.equals(getKm(), veiculo.getKm()) && Objects.equals(getCor(), veiculo.getCor()) && getTipoCombustivel() == veiculo.getTipoCombustivel() && Objects.equals(getChassi(), veiculo.getChassi()) && Objects.equals(getProprietario(), veiculo.getProprietario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlaca(), getMarca(), getModelo(), getKm(), getCor(), getAnoFabricacao(), getTipoCombustivel(), getChassi(), getProprietario());
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", km='" + km + '\'' +
                ", cor='" + cor + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", tipoCombustivel=" + tipoCombustivel +
                ", chassi='" + chassi + '\'' +
                ", proprietario=" + proprietario +
                '}';
    }
}
