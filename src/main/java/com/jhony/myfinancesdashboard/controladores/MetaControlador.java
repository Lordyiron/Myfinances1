package com.jhony.myfinancesdashboard.controladores;

import com.jhony.myfinancesdashboard.modelos.Meta;
import com.jhony.myfinancesdashboard.servicos.MetaServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MetaControlador {
    private final MetaServico metaServico;

    public MetaControlador(MetaServico metaServico) {
        this.metaServico = metaServico;
    }

    @GetMapping("/metas")
    public List<Meta> buscarMetas() {
        return metaServico.buscarMetas();
    }

    @GetMapping("/metas/{id}")
    public Meta buscarMetaPorId(@PathVariable Long id) {
        return metaServico.buscarMetaPorId(id);
    }

    @PostMapping("/metas")
    public Meta salvarMeta(@RequestBody Meta meta) {
        return metaServico.salvarMeta(meta);
    }

    @DeleteMapping("/metas/{id}")
    public void deletarMeta(@PathVariable Long id) {
        metaServico.deletarMeta(id);
    }
}
