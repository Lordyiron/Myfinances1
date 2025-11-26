package com.jhony.myfinancesdashboard.repositorios;

import com.jhony.myfinancesdashboard.modelos.AplicacaoInvestimento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AplicacaoInvestimentoRepositorio extends JpaRepository<AplicacaoInvestimento, Long> {

    List<AplicacaoInvestimento> findAllByInvestimentoId(Long investimentoId);

    @Query("select sum(valorAplicado) from AplicacaoInvestimento WHERE dataAplicacao BETWEEN :dataInicio AND :dataFim")
    BigDecimal somarAplicacoesEntreDatas(LocalDate dataInicio, LocalDate dataFim);

    @Transactional
    void deleteAllByInvestimentoId(Long investimentoId);
}
