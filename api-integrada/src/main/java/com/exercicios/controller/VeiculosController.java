package com.exercicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exercicios.model.EstatisticasVeiculos;
import com.exercicios.model.Veiculo;
import com.exercicios.service.VeiculosService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculosController {
    
    @Autowired
    private VeiculosService veiculosService;
    
    @GetMapping
    public ResponseEntity<List<Veiculo>> getAll(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Integer ano) {
        
        List<Veiculo> veiculos = veiculosService.findByFilters(marca, ano);
        return ResponseEntity.ok(veiculos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getById(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculosService.findById(id);
        return veiculo
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody Veiculo veiculo) {
        try {
            Veiculo novoVeiculo = veiculosService.save(veiculo);
            return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable Long id, @RequestBody Veiculo veiculo) throws IllegalArgumentException {
        try {
            Veiculo veiculoAtualizado = veiculosService.update(id, veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> patch(@PathVariable Long id, @RequestBody Map<String, Object> campos) throws IllegalArgumentException {
        try {
            Veiculo veiculoAtualizado = veiculosService.patch(id, campos);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            veiculosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticasVeiculos> getEstatisticas() {
        EstatisticasVeiculos estatisticas = veiculosService.getEstatisticas();
        return ResponseEntity.ok(estatisticas);
    }
    
    @GetMapping("/marcas-validas")
    public ResponseEntity<List<String>> getMarcasValidas() {
        return ResponseEntity.ok(veiculosService.getMarcasValidas());
    }
}