package com.gestionprojetagile.ProjetAgile.web.repositories;

import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> findTaskByUserStory(UserStory userStory);
    List<Task> findTaskByUtilisateur(Utilisateur utilisateur);
}
