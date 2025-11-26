package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.Banco;
import com.jhony.myfinancesdashboard.repositorios.BancoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoServico {

    private final BancoRepositorio bancoRepositorio;

    public BancoServico(BancoRepositorio bancoRepositorio) {
        this.bancoRepositorio = bancoRepositorio;
    }

    public List<Banco> buscarBancos() {
        return bancoRepositorio.findAll();
    }

    public Banco buscarBancoPorId(Long id) {
        return bancoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Banco não encontrado"));
    }

    public Banco salvarBanco(Banco banco) {
        return bancoRepositorio.save(banco);
    }

    public void deletarBanco(Long id) {
        bancoRepositorio.deleteById(id);
    }
}
