package com.jhony.myfinancesdashboard.repositorios;

import com.jhony.myfinancesdashboard.modelos.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ReceitaRepositorio extends JpaRepository<Receita, Long> {

    @Query("select sum(valorReceita) from Receita WHERE dataReceita BETWEEN :dataInicio AND :dataFim")
    BigDecimal somarReceitaEntreDatas(LocalDate dataInicio, LocalDate dataFim);
}
