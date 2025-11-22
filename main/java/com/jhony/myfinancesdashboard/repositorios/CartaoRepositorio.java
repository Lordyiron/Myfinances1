package com.jhony.myfinancesdashboard.repositorios;

import com.jhony.myfinancesdashboard.modelos.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepositorio extends JpaRepository<Cartao, Long> {
}
