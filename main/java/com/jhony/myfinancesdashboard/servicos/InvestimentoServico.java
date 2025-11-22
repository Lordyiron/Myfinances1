package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.Investimento;
import com.jhony.myfinancesdashboard.repositorios.AplicacaoInvestimentoRepositorio;
import com.jhony.myfinancesdashboard.repositorios.InvestimentoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestimentoServico {

    private final InvestimentoRepositorio investimentoRepositorio;

    public InvestimentoServico(InvestimentoRepositorio investimentoRepositorio) {
        this.investimentoRepositorio = investimentoRepositorio;
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

    public void deletarInvestimento(Long id) {
        investimentoRepositorio.deleteById(id);
    }
}
