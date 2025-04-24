package com.gestionprojetagile.ProjetAgile.web.repositories;

import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintBacklogRepo extends JpaRepository<SprintBacklog,Long> {

}
