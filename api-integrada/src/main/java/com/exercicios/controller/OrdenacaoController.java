package com.exercicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exercicios.model.ResultadoOrdenacao;
import com.exercicios.service.OrdenacaoService;
import java.util.List;

@RestController
@RequestMapping("/api/ordenacao")
public class OrdenacaoController {
    
    @Autowired
    private OrdenacaoService ordenacaoService;
    
    @PostMapping("/bubble-sort")
    public ResponseEntity<ResultadoOrdenacao> bubbleSort(@RequestBody List<Integer> vetor) {
        ResultadoOrdenacao resultado = ordenacaoService.bubbleSort(vetor);
        return ResponseEntity.ok(resultado);
    }
}