package com.springboot.bootstrap.service;

import com.springboot.bootstrap.model.Role;
import com.springboot.bootstrap.model.Users;
import com.springboot.bootstrap.repositories.RoleRepository;
import com.springboot.bootstrap.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    public void saveUser(Users user) {

        Users users;
        users = new Users( user.getName(),user.getEmail(), passwordEncoder.encode(user.getPassword()),user.getNiveau(),user.getRole());
        System.out.println("Welcome toi" +users);
        userRepository.save(users);

    }
    public Users validateUser(Users users) {

        Users user = userRepository.findByEmail(users.getEmail());
        if (user != null && passwordEncoder.matches(users.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }


    public Users findByEmail(String email) {
        return userRepository.findByEmail(email); // Assuming you have a UserRepository with findByEmail method
    }
}
