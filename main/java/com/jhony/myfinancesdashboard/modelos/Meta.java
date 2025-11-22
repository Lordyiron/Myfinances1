package com.jhony.myfinancesdashboard.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "meta_seq", sequenceName = "meta_seq", allocationSize = 1)
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meta_seq")
    private Long id;

    private String nome;

    private BigDecimal valorObjetivo;
    private BigDecimal valorAlcancado;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(BigDecimal valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public BigDecimal getValorAlcancado() {
        return valorAlcancado;
    }

    public void setValorAlcancado(BigDecimal valorAlcancado) {
        this.valorAlcancado = valorAlcancado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
