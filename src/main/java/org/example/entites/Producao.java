package org.example.entites;

public class Producao {

    private String entidade;
    private int Ano_prod;
    private int prod_plastico;


    public Producao(String entidade, int ano_prod, int prod_plastico) {
        this.entidade = entidade;
        Ano_prod = ano_prod;
        this.prod_plastico = prod_plastico;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public int getAno_prod() {
        return Ano_prod;
    }

    public void setAno_prod(int ano_prod) {
        Ano_prod = ano_prod;
    }

    public int getProd_plastico() {
        return prod_plastico;
    }

    public void setProd_plastico(int prod_plastico) {
        this.prod_plastico = prod_plastico;
    }

}
