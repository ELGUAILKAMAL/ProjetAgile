package com.gestionprojetagile.ProjetAgile.web.Enities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;


@Entity
@Table
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;


    @OneToOne(mappedBy = "project" )
    private ProductBacklog productBacklog;

    public Project() {
    }

    public Project(Long id, String name, String description, Utilisateur utilisateur, ProductBacklog productBacklog) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.utilisateur = utilisateur;
        this.productBacklog = productBacklog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }
}
