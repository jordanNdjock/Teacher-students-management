package com.springboot.bootstrap.repositories;

import com.springboot.bootstrap.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository <Cours,Long> {
}
