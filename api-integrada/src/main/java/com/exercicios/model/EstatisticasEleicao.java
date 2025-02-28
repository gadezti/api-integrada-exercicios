package com.exercicios.model;

public class EstatisticasEleicao {
    private int totalEleitores;
    private int votosValidos;
    private int votosBrancos;
    private int votosNulos;
    private double percentualValidos;
    private double percentualBrancos;
    private double percentualNulos;
    
    public EstatisticasEleicao() {}
    
    public EstatisticasEleicao(int totalEleitores, int votosValidos, int votosBrancos, int votosNulos) {
        this.totalEleitores = totalEleitores;
        this.votosValidos = votosValidos;
        this.votosBrancos = votosBrancos;
        this.votosNulos = votosNulos;
        calcularPercentuais();
    }
    
    public void calcularPercentuais() {
        this.percentualValidos = calcularPercentual(votosValidos, totalEleitores);
        this.percentualBrancos = calcularPercentual(votosBrancos, totalEleitores);
        this.percentualNulos = calcularPercentual(votosNulos, totalEleitores);
    }
    
    private double calcularPercentual(int votos, int total) {
        return (double) votos / total * 100;
    }
    
    public int getTotalEleitores() {
        return totalEleitores;
    }
    
    public void setTotalEleitores(int totalEleitores) {
        this.totalEleitores = totalEleitores;
    }
    
    public int getVotosValidos() {
        return votosValidos;
    }
    
    public void setVotosValidos(int votosValidos) {
        this.votosValidos = votosValidos;
    }
    
    public int getVotosBrancos() {
        return votosBrancos;
    }
    
    public void setVotosBrancos(int votosBrancos) {
        this.votosBrancos = votosBrancos;
    }
    
    public int getVotosNulos() {
        return votosNulos;
    }
    
    public void setVotosNulos(int votosNulos) {
        this.votosNulos = votosNulos;
    }
    
    public double getPercentualValidos() {
        return percentualValidos;
    }
    
    public double getPercentualBrancos() {
        return percentualBrancos;
    }
    
    public double getPercentualNulos() {
        return percentualNulos;
    }
}