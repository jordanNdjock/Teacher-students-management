package com.springboot.bootstrap.service;

import com.springboot.bootstrap.model.Users;
import com.springboot.bootstrap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user=userRepo.findByEmail(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Not found: " + username);
        }}

    private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        String userRole = user.getRole().getName(); // Récupère le nom du rôle depuis l'objet Role
        return AuthorityUtils.createAuthorityList(userRole); // Crée une liste d'autorités avec le rôle
    }


}