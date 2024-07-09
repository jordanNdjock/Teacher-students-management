package com.springboot.bootstrap.repositories;

import com.springboot.bootstrap.model.Ue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UeRepository extends JpaRepository<Ue, Long> {

    Ue  findByCodeAndNiv (String code, String niv);

}
