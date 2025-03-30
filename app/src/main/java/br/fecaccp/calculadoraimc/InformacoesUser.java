package br.fecaccp.calculadoraimc;

import java.io.Serializable;

public class InformacoesUser implements Serializable {

    private String peso;
    private String altura;
    private String imc;

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
}
