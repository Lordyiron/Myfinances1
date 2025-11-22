package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.AplicacaoInvestimento;
import com.jhony.myfinancesdashboard.modelos.Investimento;
import com.jhony.myfinancesdashboard.repositorios.AplicacaoInvestimentoRepositorio;
import com.jhony.myfinancesdashboard.repositorios.InvestimentoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplicacaoInvestimentoServico {

    private final AplicacaoInvestimentoRepositorio aplicacaoInvestimentoRepositorio;
    private final InvestimentoRepositorio investimentoRepositorio;

    public AplicacaoInvestimentoServico(AplicacaoInvestimentoRepositorio aplicacaoInvestimentoRepositorio, InvestimentoRepositorio investimentoRepositorio) {
        this.aplicacaoInvestimentoRepositorio = aplicacaoInvestimentoRepositorio;
        this.investimentoRepositorio = investimentoRepositorio;
    }

    @Transactional
    public AplicacaoInvestimento salvarAplicacaoInvestimento(AplicacaoInvestimento aplicacaoInvestimento) {
        long idDoInvestimento = aplicacaoInvestimento.getInvestimento().getId();
        Investimento investimento = investimentoRepositorio.findById(idDoInvestimento)
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado"));

        investimento.setTotalInvestido(
                investimento.getTotalInvestido().add(aplicacaoInvestimento.getValorAplicado())
        );
        investimentoRepositorio.save(investimento);
        return aplicacaoInvestimentoRepositorio.save(aplicacaoInvestimento);
    }

    public List<AplicacaoInvestimento> buscarAplicacoesPorIdDoInvestimento(Long id) {
        return aplicacaoInvestimentoRepositorio.findAllByInvestimentoId(id);
    }

    public void deletarAplicacaoInvestimento(Long id) {
        aplicacaoInvestimentoRepositorio.deleteById(id);
    }
}
