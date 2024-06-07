package org.example.entites;

public class Agua {

    private String Cidade;
    private String regiao;
    private String entidade;
    private float qualidade_ar;
    private float poluicao_ar;

    public Agua(String cidade, String regiao, String entidade, float qualidade_ar, float poluicao_ar) {
        Cidade = cidade;
        this.regiao = regiao;
        this.entidade = entidade;
        this.qualidade_ar = qualidade_ar;
        this.poluicao_ar = poluicao_ar;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public float getQualidade_ar() {
        return qualidade_ar;
    }

    public void setQualidade_ar(float qualidade_ar) {
        this.qualidade_ar = qualidade_ar;
    }

    public float getPoluicao_ar() {
        return poluicao_ar;
    }

    public void setPoluicao_ar(float poluicao_ar) {
        this.poluicao_ar = poluicao_ar;
    }


}
