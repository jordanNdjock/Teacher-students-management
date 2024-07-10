package com.springboot.bootstrap.configurations;

import com.springboot.bootstrap.model.Category;
import com.springboot.bootstrap.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public CommandLineRunner initCategories() {
        return args -> {
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category("TP"));
                categoryRepository.save(new Category("TD"));
                categoryRepository.save(new Category("Cours"));
            }
        };
    }
}