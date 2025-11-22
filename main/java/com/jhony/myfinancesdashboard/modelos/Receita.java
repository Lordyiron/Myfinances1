package com.jhony.myfinancesdashboard.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "receita_seq", sequenceName = "receita_seq", allocationSize = 1)
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receita_seq")
    private Long id;

    private String nome;
    private BigDecimal valorReceita;
    private LocalDate dataReceita = LocalDate.now();

    public LocalDate getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(LocalDate dataEntrada) {
        this.dataReceita = dataEntrada;
    }

    public BigDecimal getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(BigDecimal valorReceita) {
        this.valorReceita = valorReceita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
