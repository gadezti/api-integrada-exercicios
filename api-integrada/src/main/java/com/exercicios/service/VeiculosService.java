package com.exercicios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicios.model.EstatisticasVeiculos;
import com.exercicios.model.Veiculo;
import com.exercicios.repository.VeiculoRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VeiculosService {
    
    private static final List<String> MARCAS_VALIDAS = Arrays.asList(
        "Ford", "Honda", "Toyota", "Chevrolet", "Volkswagen", "BMW", "Mercedes", "Audi", 
        "Hyundai", "Kia", "Nissan", "Fiat", "Renault", "Peugeot", "Citroen", "Ferrari"
    );
    
    @Autowired
    private VeiculoRepository veiculoRepository;
    
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }
    
    public Optional<Veiculo> findById(Long id) {
        return veiculoRepository.findById(id);
    }
    
    public List<Veiculo> findByFilters(String marca, Integer ano) {
        if (marca != null && ano != null) {
            return veiculoRepository.findByMarcaAndAno(marca, ano);
        } else if (marca != null) {
            return veiculoRepository.findByMarca(marca);
        } else if (ano != null) {
            return veiculoRepository.findByAno(ano);
        } else {
            return veiculoRepository.findAll();
        }
    }
    
    public Veiculo save(Veiculo veiculo) {
        validarMarca(veiculo.getMarca());
        
        if (veiculo.getId() == null) {
            veiculo.setCreated(LocalDateTime.now());
            veiculo.setUpdated(LocalDateTime.now());
        } else {
            veiculo.setUpdated(LocalDateTime.now());
        }
        
        return veiculoRepository.save(veiculo);
    }
    
    public Veiculo update(Long id, Veiculo veiculoAtualizado) {
        validarMarca(veiculoAtualizado.getMarca());
        
        return veiculoRepository.findById(id)
            .map(veiculo -> {
                veiculo.setVeiculo(veiculoAtualizado.getVeiculo());
                veiculo.setMarca(veiculoAtualizado.getMarca());
                veiculo.setAno(veiculoAtualizado.getAno());
                veiculo.setDescricao(veiculoAtualizado.getDescricao());
                veiculo.setVendido(veiculoAtualizado.getVendido());
                veiculo.setUpdated(LocalDateTime.now());
                return veiculoRepository.save(veiculo);
            })
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado com ID: " + id));
    }
    
    public Veiculo patch(Long id, Map<String, Object> campos) {
        if (campos.containsKey("marca")) {
            validarMarca((String) campos.get("marca"));
        }
        
        return veiculoRepository.findById(id)
            .map(veiculo -> {
                if (campos.containsKey("veiculo")) {
                    veiculo.setVeiculo((String) campos.get("veiculo"));
                }
                if (campos.containsKey("marca")) {
                    veiculo.setMarca((String) campos.get("marca"));
                }
                if (campos.containsKey("ano")) {
                    veiculo.setAno((Integer) campos.get("ano"));
                }
                if (campos.containsKey("descricao")) {
                    veiculo.setDescricao((String) campos.get("descricao"));
                }
                if (campos.containsKey("vendido")) {
                    veiculo.setVendido((Boolean) campos.get("vendido"));
                }
                veiculo.setUpdated(LocalDateTime.now());
                return veiculoRepository.save(veiculo);
            })
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado com ID: " + id));
    }
    
    public void delete(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado com ID: " + id);
        }
        veiculoRepository.deleteById(id);
    }
    
    public EstatisticasVeiculos getEstatisticas() {
        EstatisticasVeiculos estatisticas = new EstatisticasVeiculos();
        
        estatisticas.setTotalVeiculos((long) veiculoRepository.findAll().size());
        estatisticas.setNaoVendidos(veiculoRepository.countByVendidoFalse());
        estatisticas.setRegistradosUltimaSemana(
            (long) veiculoRepository.findByCreatedAfter(LocalDateTime.now().minusWeeks(1)).size()
        );
        
        // Distribuição por década
        Map<String, Long> distribuicaoPorDecada = new HashMap<>();
        List<Veiculo> veiculos = veiculoRepository.findAll();
        
        for (Veiculo veiculo : veiculos) {
            String decada = "Década " + (veiculo.getAno() / 10 * 10);
            distribuicaoPorDecada.put(decada, distribuicaoPorDecada.getOrDefault(decada, 0L) + 1);
        }
        estatisticas.setDistribuicaoPorDecada(distribuicaoPorDecada);
        
        // Distribuição por fabricante
        Map<String, Long> distribuicaoPorFabricante = new HashMap<>();
        List<Object[]> countByMarca = veiculoRepository.countByMarca();
        
        for (Object[] result : countByMarca) {
            String marca = (String) result[0];
            Long count = (Long) result[1];
            distribuicaoPorFabricante.put(marca, count);
        }
        estatisticas.setDistribuicaoPorFabricante(distribuicaoPorFabricante);
        
        return estatisticas;
    }
    
    private void validarMarca(String marca) {
        if (!MARCAS_VALIDAS.contains(marca)) {
            throw new IllegalArgumentException("Marca inválida: " + marca);
        }
    }
    
    public List<String> getMarcasValidas() {
        return MARCAS_VALIDAS;
    }
}