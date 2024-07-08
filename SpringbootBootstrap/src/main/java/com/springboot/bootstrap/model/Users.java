package com.springboot.bootstrap.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String name;

    @Column(unique = true, length = 60)
    private String email;

    @Column
    private String password;

    @Column(nullable = true)
    private String niveau; // Cette colonne peut être nulle

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    public Users() {
        super();
    }

    public Users(String name, String email, String password, String niveau, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.niveau = niveau;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = getRole().getName(); // Récupère le nom du rôle depuis l'objet Role
        return AuthorityUtils.createAuthorityList(userRole); // Crée une liste d'autorités avec le rôle
    }

    // Getters et Setters

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
