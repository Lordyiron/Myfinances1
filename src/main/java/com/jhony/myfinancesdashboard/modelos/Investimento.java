package com.jhony.myfinancesdashboard.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@SequenceGenerator(name = "investimento_seq", sequenceName = "investimento_seq", allocationSize = 1)
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investimento_seq")
    private Long id;

    private String nome;
    private BigDecimal totalInvestido = BigDecimal.ZERO;
    private LocalDate dataCriacao = LocalDate.now();

    @OneToMany(mappedBy = "investimento")
    private List<AplicacaoInvestimento> aplicacoes;

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

    public BigDecimal getTotalInvestido() {
        return totalInvestido;
    }

    public void setTotalInvestido(BigDecimal totalInvestido) {
        this.totalInvestido = totalInvestido;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<AplicacaoInvestimento> getAplicacoes() {
        return aplicacoes;
    }

    public void setAplicacoes(List<AplicacaoInvestimento> aplicacoes) {
        this.aplicacoes = aplicacoes;
    }
}
