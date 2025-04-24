package com.gestionprojetagile.ProjetAgile.web.repositories;

import com.gestionprojetagile.ProjetAgile.web.Enities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {

}
