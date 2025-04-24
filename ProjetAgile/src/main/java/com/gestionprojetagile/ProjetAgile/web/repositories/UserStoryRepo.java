package com.gestionprojetagile.ProjetAgile.web.repositories;


import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface UserStoryRepo extends JpaRepository<UserStory,Long> {
    List<UserStory> findAllByProductBacklogOrderByPriority(ProductBacklog productBacklog);

    List<UserStory> findAllBySprintBacklogOrderByPriority(SprintBacklog sprintBacklog);
}

