package com.springboot.bootstrap.service;

import com.springboot.bootstrap.model.CoursFile;
import com.springboot.bootstrap.repositories.CoursFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursFileService {
    @Autowired
    private CoursFileRepository coursFileRepository;

    public CoursFile saveCoursFile(CoursFile coursFile) {
        return coursFileRepository.save(coursFile);
    }
}
