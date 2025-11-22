package com.jhony.myfinancesdashboard.repositorios;

import com.jhony.myfinancesdashboard.modelos.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepositorio extends JpaRepository<Meta, Long> {
}
