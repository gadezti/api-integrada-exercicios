package com.exercicios.service;

import org.springframework.stereotype.Service;
import java.math.BigInteger;

@Service
public class FatorialService {
    
    public BigInteger calcularFatorial(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("O número deve ser não-negativo.");
        }
        
        if (numero == 0 || numero == 1) {
            return BigInteger.ONE;
        }
        
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= numero; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        
        return resultado;
    }
    
    // Versão recursiva (opcional)
    public BigInteger calcularFatorialRecursivo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("O número deve ser não-negativo.");
        }
        
        if (numero == 0 || numero == 1) {
            return BigInteger.ONE;
        }
        
        return BigInteger.valueOf(numero).multiply(calcularFatorialRecursivo(numero - 1));
    }
}