package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.dtos.DetalhesPlanejamentoDto;
import com.jhony.myfinancesdashboard.repositorios.AplicacaoInvestimentoRepositorio;
import com.jhony.myfinancesdashboard.repositorios.DespesaRepositorio;
import com.jhony.myfinancesdashboard.repositorios.ReceitaRepositorio;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PlanejamentoFinanceiroService {

    private final ReceitaRepositorio receitaRepositorio;
    private final DespesaRepositorio despesaRepositorio;
    private final AplicacaoInvestimentoRepositorio aplicacaoInvestimentoRepositorio;

    public PlanejamentoFinanceiroService(ReceitaRepositorio receitaRepositorio, DespesaRepositorio despesaRepositorio, AplicacaoInvestimentoRepositorio aplicacaoInvestimentoRepositorio) {
        this.receitaRepositorio = receitaRepositorio;
        this.despesaRepositorio = despesaRepositorio;
        this.aplicacaoInvestimentoRepositorio = aplicacaoInvestimentoRepositorio;
    }

    public DetalhesPlanejamentoDto buscarPlanejamentoFinanceiroMensal(LocalDate data) {
        var inicioEFim = extrairInicioEFimDoMes(data);
        return new DetalhesPlanejamentoDto(
                buscarReceitaTotalMensal(inicioEFim.getFirst(), inicioEFim.getSecond()),
                buscarDespesasTotalMensal(inicioEFim.getFirst(), inicioEFim.getSecond()),
                buscarInvestimentoTotalMensal(inicioEFim.getFirst(), inicioEFim.getSecond())
        );

    }

    public BigDecimal buscarReceitaTotalMensal(LocalDate dataInicio, LocalDate dataFim) {
        return receitaRepositorio.somarReceitaEntreDatas(dataInicio, dataFim);
    }

    public BigDecimal buscarDespesasTotalMensal(LocalDate dataInicio, LocalDate dataFim) {
        return despesaRepositorio.somarDespesasEntreDatas(dataInicio, dataFim);
    }

    public BigDecimal buscarInvestimentoTotalMensal(LocalDate dataInicio, LocalDate dataFim) {
        return aplicacaoInvestimentoRepositorio.somarAplicacoesEntreDatas(dataInicio, dataFim);
    }

    private Pair<LocalDate, LocalDate> extrairInicioEFimDoMes(LocalDate data) {
        LocalDate dataInicial = data.withDayOfMonth(1);
        LocalDate dataFinal = data.withDayOfMonth(data.getDayOfMonth());

        return Pair.of(dataInicial, dataFinal);
    }

}
