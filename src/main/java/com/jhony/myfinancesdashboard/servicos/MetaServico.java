package com.jhony.myfinancesdashboard.servicos;

import com.jhony.myfinancesdashboard.modelos.Meta;
import com.jhony.myfinancesdashboard.repositorios.MetaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaServico {

    private final MetaRepositorio metaRepositorio;

    public MetaServico(MetaRepositorio metaRepositorio) {
        this.metaRepositorio = metaRepositorio;
    }

    public List<Meta> buscarMetas() {
        return metaRepositorio.findAll();
    }

    public Meta buscarMetaPorId(Long id) {
        return metaRepositorio.findById(id).orElseThrow();
    }

    public Meta salvarMeta(Meta meta) {
        return metaRepositorio.save(meta);
    }

    public void deletarMeta(Long id) {
        metaRepositorio.deleteById(id);
    }
}
