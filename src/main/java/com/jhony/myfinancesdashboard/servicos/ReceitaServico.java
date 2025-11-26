package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.Receita;
import com.jhony.myfinancesdashboard.repositorios.ReceitaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaServico {

    private final ReceitaRepositorio receitaRepositorio;

    public ReceitaServico(ReceitaRepositorio receitaRepositorio) {
        this.receitaRepositorio = receitaRepositorio;
    }

    public List<Receita> buscarReceitas() {
        return receitaRepositorio.findAll();
    }

    public Receita buscarReceitaPorId(Long id) {
        return receitaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public Receita salvarReceita(Receita receita) {
        return receitaRepositorio.save(receita);
    }

    public void deletarReceita(Long id) {
        receitaRepositorio.deleteById(id);
    }
}
