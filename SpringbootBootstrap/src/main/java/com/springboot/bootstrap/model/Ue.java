package com.springboot.bootstrap.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ues")
public class Ue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String code;

    @Column( nullable = false)
    private String intitul;

    private String niv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Ue() {
        super();
    }

    public Ue(String code, String intitul, String niv, Users user) {
        this.code = code;
        this.intitul = intitul;
        this.niv = niv;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNiv() {
        return niv;
    }

    public void setNiv(String niv) {
        this.niv = niv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitul() {
        return intitul;
    }

    public void setIntitul(String intitul) {
        this.intitul = intitul;
    }
}
