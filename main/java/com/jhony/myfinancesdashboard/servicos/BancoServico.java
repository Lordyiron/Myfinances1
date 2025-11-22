package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.repositorios.BancoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class BancoServico {

    private final BancoRepositorio bancoRepositorio;

    public BancoServico(BancoRepositorio bancoRepositorio) {
        this.bancoRepositorio = bancoRepositorio;
    }
}
