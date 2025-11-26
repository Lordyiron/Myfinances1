package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.Investimento;
import com.jhony.myfinancesdashboard.repositorios.AplicacaoInvestimentoRepositorio;
import com.jhony.myfinancesdashboard.repositorios.InvestimentoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestimentoServico {

    private final InvestimentoRepositorio investimentoRepositorio;
    private final AplicacaoInvestimentoRepositorio aplicacaoInvestimentoRepositorio;

    public InvestimentoServico(InvestimentoRepositorio investimentoRepositorio, AplicacaoInvestimentoRepositorio aplicacaoInvestimentoRepositorio) {
        this.investimentoRepositorio = investimentoRepositorio;
        this.aplicacaoInvestimentoRepositorio = aplicacaoInvestimentoRepositorio;
    }

    public List<Investimento> buscarInvestimentos() {
        return investimentoRepositorio.findAll();
    }

    public Investimento buscarInvestimentoPorId(Long id) {
        return investimentoRepositorio.findById(id).orElseThrow();
    }

    public Investimento salvarInvestimento(Investimento investimento) {
        if (investimentoRepositorio.existsByNomeIgnoreCaseAndIdNot(investimento.getNome(), investimento.getId())) {
            throw new RuntimeException("Já existe um investimento com esse nome.");
        }

        return investimentoRepositorio.save(investimento);
    }

    @Transactional
    public void deletarInvestimento(Long id) {
        aplicacaoInvestimentoRepositorio.deleteAllByInvestimentoId(id);
        investimentoRepositorio.deleteById(id);
    }
}
