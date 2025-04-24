package com.gestionprojetagile.ProjetAgile.web.service.ImplService;


import com.gestionprojetagile.ProjetAgile.web.Enities.Project;
import com.gestionprojetagile.ProjetAgile.web.repositories.ProjectRepo;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectImpl implements IProject {
    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public Project createProject(Project project) {
        try {
            return projectRepo.save(project);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du projet: " + e.getMessage());
        }
    }

    @Override
    public Project getProjectById(Long id) {
        Optional<Project> project = projectRepo.findById(id);
        return project.orElseThrow(() -> new RuntimeException("Projet avec l'ID " + id + " non trouvé"));
    }

    @Override
    public List<Project> getAllProjects() {
        try {
            return projectRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des projets: " + e.getMessage());
        }
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project existingProject = getProjectById(id);

        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());

        try {
            return projectRepo.save(existingProject);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour du projet: " + e.getMessage());
        }
    }

    @Override
    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        try {
            projectRepo.delete(project);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du projet: " + e.getMessage());
        }
    }
}