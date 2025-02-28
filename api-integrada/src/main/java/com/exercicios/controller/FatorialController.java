package com.exercicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exercicios.service.FatorialService;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fatorial")
public class FatorialController {
    
    @Autowired
    private FatorialService fatorialService;
    
    @GetMapping("/{numero}")
    public ResponseEntity<Map<String, Object>> calcularFatorial(@PathVariable int numero) {
        Map<String, Object> resultado = new HashMap<>();
        try {
            BigInteger fatorial = fatorialService.calcularFatorial(numero);
            resultado.put("numero", numero);
            resultado.put("fatorial", fatorial);
            resultado.put("status", "sucesso");
        } catch (Exception e) {
            resultado.put("erro", e.getMessage());
            resultado.put("status", "erro");
            return ResponseEntity.badRequest().body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }
}