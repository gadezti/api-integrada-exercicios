package com.exercicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exercicios.model.Veiculo;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    List<Veiculo> findByMarca(String marca);
    
    List<Veiculo> findByAno(Integer ano);
    
    List<Veiculo> findByMarcaAndAno(String marca, Integer ano);
    
    @Query("SELECT COUNT(v) FROM Veiculo v WHERE v.vendido = false")
    Long countByVendidoFalse();
    
    @Query("SELECT v FROM Veiculo v WHERE v.created >= ?1")
    List<Veiculo> findByCreatedAfter(LocalDateTime date);
    
    @Query("SELECT v.marca, COUNT(v) FROM Veiculo v GROUP BY v.marca")
    List<Object[]> countByMarca();
}