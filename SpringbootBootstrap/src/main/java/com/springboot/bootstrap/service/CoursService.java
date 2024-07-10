package com.springboot.bootstrap.service;

import com.springboot.bootstrap.model.Cours;
import com.springboot.bootstrap.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {
    private final CoursRepository coursRepository;

    @Autowired
    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    public Cours getCoursById(Long id) {
        return coursRepository.findById(id).orElse(null);
    }

    public Cours saveCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteCours(Long id) {
        coursRepository.deleteById(id);
    }
}