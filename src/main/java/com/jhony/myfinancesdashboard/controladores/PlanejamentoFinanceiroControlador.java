package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.dtos.DetalhesPlanejamentoDto;
import com.jhony.myfinancesdashboard.servicos.PlanejamentoFinanceiroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class PlanejamentoFinanceiroControlador {

    private final PlanejamentoFinanceiroService planejamentoFinanceiroService;

    public PlanejamentoFinanceiroControlador(PlanejamentoFinanceiroService planejamentoFinanceiroService) {
        this.planejamentoFinanceiroService = planejamentoFinanceiroService;
    }

    @GetMapping("/planejamento")
    public DetalhesPlanejamentoDto buscarPlanejamentoFinanceiroMensal() {
        return planejamentoFinanceiroService.buscarPlanejamentoFinanceiroMensal(LocalDate.now());
    }
}
