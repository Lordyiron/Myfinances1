package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.modelos.Banco;
import com.jhony.myfinancesdashboard.servicos.BancoServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BancoControlador {

    private final BancoServico bancoServico;

    public BancoControlador(BancoServico bancoServico) {
        this.bancoServico = bancoServico;
    }

    @GetMapping("/bancos")
    public List<Banco> buscarBancos() {
        return bancoServico.buscarBancos();
    }

    @GetMapping("/bancos/{id}")
    public Banco buscarBancoPorId(@PathVariable Long id) {
        return bancoServico.buscarBancoPorId(id);
    }

    @PostMapping("/bancos")
    public Banco salvarBanco(@RequestBody Banco banco) {
        return bancoServico.salvarBanco(banco);
    }

    @DeleteMapping("/bancos/{id}")
    public void deletarBanco(@PathVariable Long id) {
        bancoServico.deletarBanco(id);
    }
}

