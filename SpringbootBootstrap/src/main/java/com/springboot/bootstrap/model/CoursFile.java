package com.springboot.bootstrap.model;

import jakarta.persistence.*;

@Entity
public class CoursFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private byte[] file;

    @OneToOne
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;

    // Constructors, getters, and setters
    public CoursFile() {}

    public CoursFile(byte[] file, Cours cours) {
        this.file = file;
        this.cours = cours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }
}
