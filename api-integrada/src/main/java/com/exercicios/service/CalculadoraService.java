package com.exercicios.service;

import org.springframework.stereotype.Service;
import com.exercicios.model.EstatisticasEleicao;

@Service
public class CalculadoraService {
    
    public EstatisticasEleicao calcularEstatisticasEleicao(int totalEleitores, int votosValidos, int votosBrancos, int votosNulos) {
        return new EstatisticasEleicao(totalEleitores, votosValidos, votosBrancos, votosNulos);
    }
}