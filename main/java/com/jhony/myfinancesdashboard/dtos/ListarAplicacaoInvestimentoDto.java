package com.jhony.myfinancesdashboard.dtos;

import com.jhony.myfinancesdashboard.modelos.AplicacaoInvestimento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ListarAplicacaoInvestimentoDto {
    private Long id;
    private BigDecimal valorAplicado;
    private LocalDate dataAplicacao;

    public ListarAplicacaoInvestimentoDto() {
    }

    public ListarAplicacaoInvestimentoDto(AplicacaoInvestimento aplicacaoInvestimento) {
        this.id = aplicacaoInvestimento.getId();
        this.valorAplicado = aplicacaoInvestimento.getValorAplicado();
        this.dataAplicacao = aplicacaoInvestimento.getDataAplicacao();
    }

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
}
