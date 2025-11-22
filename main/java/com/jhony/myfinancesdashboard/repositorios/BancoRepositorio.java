package com.jhony.myfinancesdashboard.repositorios;

import com.jhony.myfinancesdashboard.modelos.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepositorio extends JpaRepository<Banco, Long> {
}
