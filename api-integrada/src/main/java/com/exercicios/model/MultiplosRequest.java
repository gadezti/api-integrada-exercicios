package com.exercicios.model;

public class MultiplosRequest {
    private int limite;
    
    public MultiplosRequest() {}
    
    public MultiplosRequest(int limite) {
        this.limite = limite;
    }
    
    public int getLimite() {
        return limite;
    }
    
    public void setLimite(int limite) {
        this.limite = limite;
    }
}