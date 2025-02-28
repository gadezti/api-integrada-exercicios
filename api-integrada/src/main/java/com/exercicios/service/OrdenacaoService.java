package com.exercicios.service;

import org.springframework.stereotype.Service;
import com.exercicios.model.ResultadoOrdenacao;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenacaoService {
    
    public ResultadoOrdenacao bubbleSort(List<Integer> vetor) {
        ResultadoOrdenacao resultado = new ResultadoOrdenacao();
        resultado.setVetorOriginal(new ArrayList<>(vetor));
        
        // Cria uma cópia do vetor para ser ordenado
        List<Integer> vetorOrdenado = new ArrayList<>(vetor);
        int n = vetorOrdenado.size();
        
        for (int i = 0; i < n - 1; i++) {
            boolean trocouNaIteracao = false;
            resultado.adicionarPasso("Iteração " + (i + 1) + ":");
            
            for (int j = 0; j < n - i - 1; j++) {
                // Compara elementos adjacentes
                String estadoAtual = estadoAtualVetor(vetorOrdenado, j, j + 1);
                resultado.adicionarPasso("Comparando posições " + j + " e " + (j + 1) + ": " + estadoAtual);
                
                if (vetorOrdenado.get(j) > vetorOrdenado.get(j + 1)) {
                    // Troca os elementos
                    int temp = vetorOrdenado.get(j);
                    vetorOrdenado.set(j, vetorOrdenado.get(j + 1));
                    vetorOrdenado.set(j + 1, temp);
                    trocouNaIteracao = true;
                    
                    String estadoAposTroca = estadoAtualVetor(vetorOrdenado, j, j + 1);
                    resultado.adicionarPasso("Trocou: " + estadoAposTroca);
                } else {
                    resultado.adicionarPasso("Manteve: " + estadoAtual);
                }
            }
            
            // Se não houve troca nesta iteração, interrompe o processo
            if (!trocouNaIteracao) {
                resultado.adicionarPasso("Não houve trocas nesta iteração. Vetor já está ordenado.");
                break;
            }
        }
        
        resultado.setVetorOrdenado(vetorOrdenado);
        return resultado;
    }
    
    private String estadoAtualVetor(List<Integer> vetor, int pos1, int pos2) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < vetor.size(); i++) {
            if (i == pos1) {
                sb.append("(").append(vetor.get(i)).append(") ");
            } else if (i == pos2) {
                sb.append("(").append(vetor.get(i)).append(") ");
            } else {
                sb.append(vetor.get(i)).append(" ");
            }
        }
        
        return sb.toString().trim();
    }
}