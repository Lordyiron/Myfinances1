package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.modelos.Cartao;
import com.jhony.myfinancesdashboard.servicos.CartaoServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartaoControlador {

    private final CartaoServico cartaoServico;

    public CartaoControlador(CartaoServico cartaoServico) {
        this.cartaoServico = cartaoServico;
    }

    @GetMapping("/cartoes")
    public List<Cartao> buscarCartoes() {
        return cartaoServico.buscarCartoes();
    }

    @GetMapping("/cartoes/{id}")
    public Cartao buscarCartaoPorId(@PathVariable Long id) {
        return cartaoServico.buscarCartaoPorId(id);
    }

    @PostMapping("/cartoes")
    public Cartao salvarCartao(@RequestBody Cartao cartao) {
        return cartaoServico.salvarCartao(cartao);
    }

    @DeleteMapping("/cartoes/{id}")
    public void deletarCartao(@PathVariable Long id) {
        cartaoServico.deletarCartao(id);
    }
}
