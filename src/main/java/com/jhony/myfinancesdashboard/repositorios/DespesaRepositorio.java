package com.jhony.myfinancesdashboard.repositorios;

import com.jhony.myfinancesdashboard.modelos.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface DespesaRepositorio extends JpaRepository<Despesa, Long> {


    @Query("select sum(valor) from Despesa WHERE vencimento BETWEEN :dataInicio AND :dataFim")
    BigDecimal somarDespesasEntreDatas(LocalDate dataInicio, LocalDate dataFim);
}
