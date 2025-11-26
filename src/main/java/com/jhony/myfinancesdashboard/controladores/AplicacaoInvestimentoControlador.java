package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.modelos.AplicacaoInvestimento;
import com.jhony.myfinancesdashboard.servicos.AplicacaoInvestimentoServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AplicacaoInvestimentoControlador {

    private final AplicacaoInvestimentoServico aplicacaoInvestimentoServico;

    public AplicacaoInvestimentoControlador(AplicacaoInvestimentoServico aplicacaoInvestimentoServico) {
        this.aplicacaoInvestimentoServico = aplicacaoInvestimentoServico;
    }


    @GetMapping("/investimentos/{id}/aplicacoes")
    public List<AplicacaoInvestimento> buscarAplicacoesPorIdDoInvestimento(@PathVariable Long id) {
        return aplicacaoInvestimentoServico.buscarAplicacoesPorIdDoInvestimento(id);
    }

    @PostMapping("/aplicacoes")
    public AplicacaoInvestimento salvarAplicacaoInvestimento(@RequestBody AplicacaoInvestimento investimento) {
        return aplicacaoInvestimentoServico.salvarAplicacaoInvestimento(investimento);
    }

    @DeleteMapping("/aplicacoes/{id}")
    public void deletarAplicacaoInvestimento(@PathVariable Long id) {
        aplicacaoInvestimentoServico.deletarAplicacaoInvestimento(id);
    }
}
