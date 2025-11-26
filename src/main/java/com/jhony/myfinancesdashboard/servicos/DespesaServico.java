package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.Despesa;
import com.jhony.myfinancesdashboard.repositorios.DespesaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaServico {

    private final DespesaRepositorio despesaRepositorio;

    public DespesaServico(DespesaRepositorio despesaRepositorio) {
        this.despesaRepositorio = despesaRepositorio;
    }

    public List<Despesa> buscarDespesas() {
        return despesaRepositorio.findAll();
    }

    public Despesa buscarDespesaPorId(Long id) {
        return despesaRepositorio.findById(id).orElseThrow();
    }

    public Despesa salvarDespesa(Despesa despesa) {
        return despesaRepositorio.save(despesa);
    }

    public void deletarDespesa(Long id) {
        despesaRepositorio.deleteById(id);
    }
}
