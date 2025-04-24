package com.gestionprojetagile.ProjetAgile.web.Enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Entity
@Table
public class SprintBacklog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;


    @OneToMany(mappedBy = "sprintBacklog" , fetch = FetchType.LAZY)
    private Collection<UserStory> userstories;

    public SprintBacklog() {
    }

    public SprintBacklog(Collection<UserStory> userstories, Date endDate, Date startDate, String name, Long id) {
        this.userstories = userstories;
        this.endDate = endDate;
        this.startDate = startDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Collection<UserStory> getUserstories() {
        return userstories;
    }

    public void setUserstories(Collection<UserStory> userstories) {
        this.userstories = userstories;
    }
}
