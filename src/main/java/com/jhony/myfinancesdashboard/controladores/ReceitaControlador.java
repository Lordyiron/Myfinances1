package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.modelos.Receita;
import com.jhony.myfinancesdashboard.servicos.ReceitaServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReceitaControlador {

    private final ReceitaServico receitaServico;

    public ReceitaControlador(ReceitaServico receitaServico) {
        this.receitaServico = receitaServico;
    }

    @GetMapping("/receitas")
    public List<Receita> buscarReceitas() {
        return receitaServico.buscarReceitas();
    }

    @GetMapping("/receitas/{id}")
    public Receita buscarReceitaPorId(@PathVariable Long id) {
        return receitaServico.buscarReceitaPorId(id);
    }

    @PostMapping("/receitas")
    public Receita salvarReceita(@RequestBody Receita Receita) {
        return receitaServico.salvarReceita(Receita);
    }

    @DeleteMapping("/receitas/{id}")
    public void deletarReceita(@PathVariable Long id) {
        receitaServico.deletarReceita(id);
    }
}
