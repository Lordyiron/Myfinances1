package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.dtos.ListarInvestimentoDto;
import com.jhony.myfinancesdashboard.modelos.Cartao;
import com.jhony.myfinancesdashboard.modelos.Investimento;
import com.jhony.myfinancesdashboard.servicos.InvestimentoServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvestimentoControlador {

    private final InvestimentoServico investimentoServico;

    public InvestimentoControlador(InvestimentoServico investimentoServico) {
        this.investimentoServico = investimentoServico;
    }

    @GetMapping("/investimentos")
    public List<ListarInvestimentoDto> buscarInvestimentos() {
        return investimentoServico.buscarInvestimentos().stream().map(ListarInvestimentoDto::new).toList();
    }

    @GetMapping("/investimentos/{id}")
    public ListarInvestimentoDto buscarInvestimentoPorId(@PathVariable Long id) {
        return new ListarInvestimentoDto(investimentoServico.buscarInvestimentoPorId(id));
    }

    @PostMapping("/investimentos")
    public ListarInvestimentoDto salvarInvestimento(@RequestBody Investimento investimento) {
        return new ListarInvestimentoDto(investimentoServico.salvarInvestimento(investimento));
    }

    @DeleteMapping("/investimentos/{id}")
    public void deletarInvestimento(@PathVariable Long id) {
        investimentoServico.deletarInvestimento(id);
    }
}
