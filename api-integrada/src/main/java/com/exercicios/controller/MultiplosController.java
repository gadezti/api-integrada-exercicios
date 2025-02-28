package com.exercicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exercicios.model.MultiplosRequest;
import com.exercicios.service.MultiplosService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/multiplos")
public class MultiplosController {
    
    @Autowired
    private MultiplosService multiplosService;
    
    @GetMapping("/{limite}")
    public ResponseEntity<Map<String, Object>> calcularMultiplos(@PathVariable int limite) {
        Map<String, Object> resultado = new HashMap<>();
        List<Integer> multiplos = multiplosService.encontrarMultiplos(limite);
        int soma = multiplosService.calcularSomaMultiplos(limite);
        
        resultado.put("limite", limite);
        resultado.put("multiplos", multiplos);
        resultado.put("soma", soma);
        
        return ResponseEntity.ok(resultado);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> calcularMultiplosPost(@RequestBody MultiplosRequest request) {
        return calcularMultiplos(request.getLimite());
    }
}