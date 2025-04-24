package com.gestionprojetagile.ProjetAgile.web.Enities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    //nqssmo description L en tant que , je souhaite pour aue
    private String entanque;
    private String jesouhaite;
    private String afinde;

    //ajouter l entite criteria d acceptance
    //ajouter le sprint different de sprintbacklog, il la date debut date fin et periode
    private String priority;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;

    @ManyToOne
    @JoinColumn(name = "springbacklog_id")
    private SprintBacklog sprintBacklog;

    @ManyToOne
    @JoinColumn(name = "productbacklog_id")
    private ProductBacklog productBacklog;

    @OneToMany(mappedBy = "userStory" , fetch = FetchType.LAZY)
    private Collection<Task> tasks;

    public UserStory() {
    }

    public UserStory(Collection<Task> tasks, ProductBacklog productBacklog, SprintBacklog sprintBacklog, Epic epic, String status, String priority, String afinde, String jesouhaite, String entanque, String title, Long id) {
        this.tasks = tasks;
        this.productBacklog = productBacklog;
        this.sprintBacklog = sprintBacklog;
        this.epic = epic;
        this.status = status;
        this.priority = priority;
        this.afinde = afinde;
        this.jesouhaite = jesouhaite;
        this.entanque = entanque;
        this.title = title;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntanque() {
        return entanque;
    }

    public void setEntanque(String entanque) {
        this.entanque = entanque;
    }

    public String getJesouhaite() {
        return jesouhaite;
    }

    public void setJesouhaite(String jesouhaite) {
        this.jesouhaite = jesouhaite;
    }

    public String getAfinde() {
        return afinde;
    }

    public void setAfinde(String afinde) {
        this.afinde = afinde;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public SprintBacklog getSprintBacklog() {
        return sprintBacklog;
    }

    public void setSprintBacklog(SprintBacklog sprintBacklog) {
        this.sprintBacklog = sprintBacklog;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }
}
