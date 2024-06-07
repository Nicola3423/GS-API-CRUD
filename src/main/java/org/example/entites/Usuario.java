package org.example.entites;

import java.util.HashMap;
import java.util.Map;

public class Usuario {


    private String NOME;
    private String EMAIL;
    private int CONTATO;
    private int CEP;
    private String LOGIN;

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }


    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Integer getCONTATO() {
        return CONTATO;
    }

    public void setCONTATO(Integer CONTATO) {
        this.CONTATO = CONTATO;
    }




    public Usuario(){}

    public Usuario(String NOME, String EMAIL, Integer CONTATO, int CEP, String LOGIN){
        this.NOME = NOME;
        this.EMAIL = EMAIL;
        this.CONTATO = CONTATO;
        this.CEP = CEP;
        this.LOGIN = LOGIN;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + NOME+ '\'' +
                ", email=" + EMAIL +
                ", contato=" + CONTATO +
                ", cep=" + CEP +
                ", login=" + LOGIN +
                '}';
    }
    public Map<Boolean, String> validate() {
        var result = new HashMap<Boolean, String>();
        if (NOME == null || NOME.isEmpty()) {
            result.put(false, "Nome não pode estar vazio");
        }
        if (EMAIL == null || EMAIL.isEmpty()) {
            result.put(false, "Email não pode estar vazio");
        }
        if(LOGIN == null || LOGIN.isEmpty()){
            result.put(false, "Login não pode estar vazio");
        }
        if (!result.containsKey(false)) {
            result.put(true, "Validação bem-sucedida");
        }
        return result;
    }
}