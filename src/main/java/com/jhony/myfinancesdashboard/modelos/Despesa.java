package com.jhony.myfinancesdashboard.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "despesa_seq", sequenceName = "despesa_seq", allocationSize = 1)
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "despesa_seq")
    private Long id;

    @ManyToOne
    private Cartao cartaoUsado;

    private String descricao;
    private BigDecimal valor;
    private int parcelas;
    private LocalDate vencimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cartao getCartaoUsado() {
        return cartaoUsado;
    }

    public void setCartaoUsado(Cartao cartaoUsado) {
        this.cartaoUsado = cartaoUsado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }
}
