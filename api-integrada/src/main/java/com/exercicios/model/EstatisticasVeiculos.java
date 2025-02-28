package com.exercicios.model;

import java.util.Map;

public class EstatisticasVeiculos {
    private Long totalVeiculos;
    private Long naoVendidos;
    private Map<String, Long> distribuicaoPorDecada;
    private Map<String, Long> distribuicaoPorFabricante;
    private Long registradosUltimaSemana;
    
    public EstatisticasVeiculos() {}
    
    public Long getTotalVeiculos() {
        return totalVeiculos;
    }
    
    public void setTotalVeiculos(Long totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }
    
    public Long getNaoVendidos() {
        return naoVendidos;
    }
    
    public void setNaoVendidos(Long naoVendidos) {
        this.naoVendidos = naoVendidos;
    }
    
    public Map<String, Long> getDistribuicaoPorDecada() {
        return distribuicaoPorDecada;
    }
    
    public void setDistribuicaoPorDecada(Map<String, Long> distribuicaoPorDecada) {
        this.distribuicaoPorDecada = distribuicaoPorDecada;
    }
    
    public Map<String, Long> getDistribuicaoPorFabricante() {
        return distribuicaoPorFabricante;
    }
    
    public void setDistribuicaoPorFabricante(Map<String, Long> distribuicaoPorFabricante) {
        this.distribuicaoPorFabricante = distribuicaoPorFabricante;
    }
    
    public Long getRegistradosUltimaSemana() {
        return registradosUltimaSemana;
    }
    
    public void setRegistradosUltimaSemana(Long registradosUltimaSemana) {
        this.registradosUltimaSemana = registradosUltimaSemana;
    }
}