package com.exercicios.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String veiculo;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
    private Integer ano;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false)
    private Boolean vendido = false;
    
    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();
    
    @Column(nullable = false)
    private LocalDateTime updated = LocalDateTime.now();
    
    public Veiculo() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public Integer getAno() {
        return ano;
    }
    
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Boolean getVendido() {
        return vendido;
    }
    
    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }
    
    public LocalDateTime getCreated() {
        return created;
    }
    
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    
    public LocalDateTime getUpdated() {
        return updated;
    }
    
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}