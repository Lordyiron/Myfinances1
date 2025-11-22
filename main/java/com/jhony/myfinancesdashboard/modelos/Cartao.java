package com.jhony.myfinancesdashboard.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "cartao_seq", sequenceName = "cartao_seq", allocationSize = 1)
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartao_seq")
    private Long id;

    @ManyToOne
    private Banco bancoEmissor;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoPagamentoCartao tipoPagamentoCartao;
    private BigDecimal limite;
    private String bandeira;
    private String numero;
    private int diaVencimentoFatura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Banco getBancoEmissor() {
        return bancoEmissor;
    }

    public void setBancoEmissor(Banco bancoEmissor) {
        this.bancoEmissor = bancoEmissor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPagamentoCartao getTipoPagamentoCartao() {
        return tipoPagamentoCartao;
    }

    public void setTipoPagamentoCartao(TipoPagamentoCartao tipoPagamentoCartao) {
        this.tipoPagamentoCartao = tipoPagamentoCartao;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getDiaVencimentoFatura() {
        return diaVencimentoFatura;
    }

    public void setDiaVencimentoFatura(int diaVencimentoFatura) {
        this.diaVencimentoFatura = diaVencimentoFatura;
    }
}
