package com.jhony.myfinancesdashboard.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "aplicacao_investimento_seq", sequenceName = "aplicacao_investimento_seq", allocationSize = 1)
public class AplicacaoInvestimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aplicacao_investimento_seq")
    private Long id;

    private BigDecimal valorAplicado;
    private LocalDate dataAplicacao = LocalDate.now();

    @ManyToOne
    private Investimento investimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorAplicado() {
        return valorAplicado;
    }

    public void setValorAplicado(BigDecimal valorAplicado) {
        this.valorAplicado = valorAplicado;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Investimento getInvestimento() {
        return investimento;
    }

    public void setInvestimento(Investimento investimento) {
        this.investimento = investimento;
    }
}
