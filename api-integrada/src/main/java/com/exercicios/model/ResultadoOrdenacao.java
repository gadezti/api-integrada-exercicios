package com.exercicios.model;

import java.util.List;
import java.util.ArrayList;

public class ResultadoOrdenacao {
    private List<Integer> vetorOriginal;
    private List<Integer> vetorOrdenado;
    private List<String> passos;
    
    public ResultadoOrdenacao() {
        this.passos = new ArrayList<>();
    }
    
    public ResultadoOrdenacao(List<Integer> vetorOriginal, List<Integer> vetorOrdenado, List<String> passos) {
        this.vetorOriginal = vetorOriginal;
        this.vetorOrdenado = vetorOrdenado;
        this.passos = passos;
    }
    
    public List<Integer> getVetorOriginal() {
        return vetorOriginal;
    }
    
    public void setVetorOriginal(List<Integer> vetorOriginal) {
        this.vetorOriginal = vetorOriginal;
    }
    
    public List<Integer> getVetorOrdenado() {
        return vetorOrdenado;
    }
    
    public void setVetorOrdenado(List<Integer> vetorOrdenado) {
        this.vetorOrdenado = vetorOrdenado;
    }
    
    public List<String> getPassos() {
        return passos;
    }
    
    public void setPassos(List<String> passos) {
        this.passos = passos;
    }
    
    public void adicionarPasso(String passo) {
        this.passos.add(passo);
    }
}