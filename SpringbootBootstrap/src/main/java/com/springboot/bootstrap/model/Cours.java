package com.springboot.bootstrap.model;

import jakarta.persistence.*;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Lob
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "ue_id", nullable = false)
    private Ue ue;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category
            category;

    // Constructors, getters, and setters
    public Cours() {
    }

    public Cours(String title, String description, byte[] file, Ue ue, Category category) {
        this.title = title;
        this.description = description;
        this.file = file;
        this.ue = ue;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Ue getUe() {
        return ue;
    }

    public void setUe(Ue ue) {
        this.ue = ue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    // Getters, setters, and constructors
}