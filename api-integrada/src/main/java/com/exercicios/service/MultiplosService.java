package com.exercicios.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MultiplosService {
    
    public List<Integer> encontrarMultiplos(int limite) {
        List<Integer> multiplos = new ArrayList<>();
        
        for (int i = 1; i < limite; i++) {  // Note o < em vez de <=
            if (i % 3 == 0 || i % 5 == 0) {
                multiplos.add(i);
            }
        }
        
        return multiplos;
    }
    
    public int calcularSomaMultiplos(int limite) {
        List<Integer> multiplos = encontrarMultiplos(limite);
        int soma = 0;
        
        for (int multiplo : multiplos) {
            soma += multiplo;
        }
        
        return soma;
    }
}
