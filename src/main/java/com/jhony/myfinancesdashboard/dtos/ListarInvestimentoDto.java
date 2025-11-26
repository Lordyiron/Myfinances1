package com.jhony.myfinancesdashboard.dtos;

import com.jhony.myfinancesdashboard.modelos.Investimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ListarInvestimentoDto {

    private Long id;
    private String nome;
    private BigDecimal totalInvestido;
    private LocalDate dataCriacao;
    private List<ListarAplicacaoInvestimentoDto> aplicacoes;

    public ListarInvestimentoDto() {
    }

    public ListarInvestimentoDto(Investimento investimento) {
        this.id = investimento.getId();
        this.nome = investimento.getNome();
        this.totalInvestido = investimento.getTotalInvestido();
        this.dataCriacao = investimento.getDataCriacao();
        this.aplicacoes = investimento.getAplicacoes().stream().map(ListarAplicacaoInvestimentoDto::new).toList();
    }

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

    public List<ListarAplicacaoInvestimentoDto> getAplicacoes() {
        return aplicacoes;
    }

    public void setAplicacoes(List<ListarAplicacaoInvestimentoDto> aplicacoes) {
        this.aplicacoes = aplicacoes;
    }
}
