package com.springboot.bootstrap.service;

import com.springboot.bootstrap.model.Ue;
import com.springboot.bootstrap.model.Users;
import com.springboot.bootstrap.repositories.RoleRepository;
import com.springboot.bootstrap.repositories.UeRepository;
import com.springboot.bootstrap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UeService {
    @Autowired
    private UeRepository ueRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUe(Ue ue) {
        ueRepository.save(ue);
    }
    public Ue getUeById(Long id) {
        return ueRepository.findById(id).orElse(null);
    }
    public List<Ue> getAllUes() {
        return (List<Ue>) ueRepository.findAll();
    }

    public boolean existsByUserAndNiv(Users user, String niv) {
        return ueRepository.existsByUserAndNiv(user, niv);
    }

    public void deleteById(Long id) {
        ueRepository.deleteById(id);
    }

}
