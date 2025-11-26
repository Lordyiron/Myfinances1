package com.jhony.myfinancesdashboard.dtos;

import java.math.BigDecimal;
import java.util.Optional;

public class DetalhesPlanejamentoDto {

    private BigDecimal receitaTotal;
    private BigDecimal despesaTotal;
    private BigDecimal poupancaTotal;

    public DetalhesPlanejamentoDto() {
    }

    public DetalhesPlanejamentoDto(BigDecimal receitaTotal, BigDecimal despesaTotal, BigDecimal poupancaTotal) {
        this.receitaTotal = receitaTotal;
        this.despesaTotal = despesaTotal;
        this.poupancaTotal = poupancaTotal;
    }

    public BigDecimal getPoupancaTotal() {
        return poupancaTotal;
    }

    public void setPoupancaTotal(BigDecimal poupancaTotal) {
        this.poupancaTotal = poupancaTotal;
    }

    public BigDecimal getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(BigDecimal despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    public BigDecimal getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(BigDecimal receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public BigDecimal getSaldoLivre() {
        return Optional.ofNullable(receitaTotal).orElse(BigDecimal.ZERO).subtract(
                Optional.ofNullable(despesaTotal).orElse(BigDecimal.ZERO))
                .subtract(Optional.ofNullable(poupancaTotal).orElse(BigDecimal.ZERO));
    }
}
