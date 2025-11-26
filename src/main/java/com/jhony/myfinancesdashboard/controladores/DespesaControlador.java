package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.modelos.Despesa;
import com.jhony.myfinancesdashboard.servicos.DespesaServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DespesaControlador {

    private final DespesaServico despesaServico;

    public DespesaControlador(DespesaServico despesaServico) {
        this.despesaServico = despesaServico;
    }

    @GetMapping("/despesas")
    public List<Despesa> buscarDespesas() {
        return despesaServico.buscarDespesas();
    }

    @GetMapping("/despesas/{id}")
    public Despesa buscarDespesaPorId(@PathVariable long id) {
        return despesaServico.buscarDespesaPorId(id);
    }

    @PostMapping("/despesas")
    public Despesa salvarDespesa(@RequestBody Despesa despesa) {
        return despesaServico.salvarDespesa(despesa);
    }

    @DeleteMapping("/despesas/{id}")
    public void deletarDespesa(@PathVariable long id) {
         despesaServico.deletarDespesa(id);
    }
}
