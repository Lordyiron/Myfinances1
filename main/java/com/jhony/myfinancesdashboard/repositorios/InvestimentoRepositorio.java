package com.jhony.myfinancesdashboard.repositorios;

import com.jhony.myfinancesdashboard.modelos.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface InvestimentoRepositorio extends JpaRepository<Investimento, Long> {

    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Long id);

}
