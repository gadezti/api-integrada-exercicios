package com.exercicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exercicios.model.EstatisticasEleicao;
import com.exercicios.service.CalculadoraService;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {
    
    @Autowired
    private CalculadoraService calculadoraService;
    
    @PostMapping("/eleicao")
    public ResponseEntity<EstatisticasEleicao> calcularEstatisticasEleicao(@RequestBody EstatisticasEleicao eleicao) {
        EstatisticasEleicao resultado = calculadoraService.calcularEstatisticasEleicao(
            eleicao.getTotalEleitores(),
            eleicao.getVotosValidos(),
            eleicao.getVotosBrancos(),
            eleicao.getVotosNulos()
        );
        return ResponseEntity.ok(resultado);
    }
}