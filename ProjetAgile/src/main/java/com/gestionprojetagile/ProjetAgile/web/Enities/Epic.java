package com.gestionprojetagile.ProjetAgile.web.Enities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table
public class Epic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;


    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    @OneToMany(mappedBy = "epic" , fetch = FetchType.LAZY)
    private List<UserStory> userStories;

    @ManyToOne
    @JoinColumn(name = "productbacklog_id")
    private ProductBacklog productBacklog;

    public Epic() {
    }

    public Epic(Long id, String name, String description, Project project, List<UserStory> userStories, ProductBacklog productBacklog) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.project = project;
        this.userStories = userStories;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }
}
