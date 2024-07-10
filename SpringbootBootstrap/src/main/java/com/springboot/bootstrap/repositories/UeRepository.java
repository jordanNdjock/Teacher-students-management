package com.springboot.bootstrap.repositories;

import com.springboot.bootstrap.model.Ue;
import com.springboot.bootstrap.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UeRepository extends JpaRepository<Ue, Long> {

    boolean existsByUserAndNiv(Users user, String niv);

}
