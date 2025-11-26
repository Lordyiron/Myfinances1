package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.Cartao;
import com.jhony.myfinancesdashboard.repositorios.CartaoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoServico {

    private final CartaoRepositorio cartaoRepositorio;

    public CartaoServico(CartaoRepositorio cartaoRepositorio) {
        this.cartaoRepositorio = cartaoRepositorio;
    }

    public List<Cartao> buscarCartoes() {
        return cartaoRepositorio.findAll();
    }

    public Cartao buscarCartaoPorId(Long id) {
        return cartaoRepositorio.findById(id).orElseThrow();
    }

    public Cartao salvarCartao(Cartao cartao) {
        return cartaoRepositorio.save(cartao);
    }

    public void deletarCartao(Long id) {
        cartaoRepositorio.deleteById(id);
    }
}
