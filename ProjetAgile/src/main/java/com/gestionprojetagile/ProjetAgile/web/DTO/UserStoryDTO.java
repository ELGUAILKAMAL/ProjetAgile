package com.gestionprojetagile.ProjetAgile.web.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserStoryDTO {
    private Long id;
    private String title;
    private String entanque;
    private String jesouhaite;
    private String afinde;
    private String priority;
    private String status;

    public UserStoryDTO() {
    }

    public UserStoryDTO(Long id, String title, String entanque, String jesouhaite, String afinde, String priority, String status) {
        this.id = id;
        this.title = title;
        this.entanque = entanque;
        this.jesouhaite = jesouhaite;
        this.afinde = afinde;
        this.priority = priority;
        this.status = status;
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
}
