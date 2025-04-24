package com.gestionprojetagile.ProjetAgile.web.Enities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table
public class ProductBacklog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "productBacklog" , fetch = FetchType.LAZY)
    private List<Epic> epics;

    @OneToMany(mappedBy = "productBacklog" , fetch = FetchType.LAZY)
    private List<UserStory> userStories;

    public ProductBacklog() {
    }

    public ProductBacklog(List<UserStory> userStories, List<Epic> epics, Project project, String name, Long id) {
        this.userStories = userStories;
        this.epics = epics;
        this.project = project;
        this.name = name;
        this.id = id;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Epic> getEpics() {
        return epics;
    }

    public void setEpics(List<Epic> epics) {
        this.epics = epics;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }
}
