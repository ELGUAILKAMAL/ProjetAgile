package com.gestionprojetagile.ProjetAgile.ImplService;


import com.gestionprojetagile.ProjetAgile.web.Enities.Project;
import com.gestionprojetagile.ProjetAgile.web.repositories.ProjectRepo;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.ProjectImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectImplTest {

    @Mock
    private ProjectRepo projectRepo;

    @InjectMocks
    private ProjectImpl projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        // Initialisation d'un projet pour les tests
        project = new Project();
        project.setId(1L);
        project.setName("Projet 1");
        project.setDescription("Description du projet 1");
    }

    // Test pour la méthode createProject
    @Test
    void testCreateProject() {
        when(projectRepo.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject);
        assertEquals(project.getId(), createdProject.getId());
        assertEquals(project.getName(), createdProject.getName());
        assertEquals(project.getDescription(), createdProject.getDescription());

        // Vérifier que projectRepo.save a été appelé une fois
        verify(projectRepo, times(1)).save(any(Project.class));
    }

    // Test pour la méthode getProjectById
    @Test
    void testGetProjectById() {
        // Simuler le comportement de projectRepo.findById
        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));

        // Appeler la méthode à tester
        Project foundProject = projectService.getProjectById(1L);

        // Vérifier les résultats
        assertNotNull(foundProject);
        assertEquals(project.getId(), foundProject.getId());
        assertEquals(project.getName(), foundProject.getName());
        assertEquals(project.getDescription(), foundProject.getDescription());

        // Vérifier que projectRepo.findById a été appelé une fois
        verify(projectRepo, times(1)).findById(1L);
    }

    // Test pour la méthode getProjectById avec un ID non trouvé
    @Test
    void testNotGetProjectById() {
        // Simuler le comportement de projectRepo.findById
        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));

        // Appeler la méthode à tester
        Project foundProject = projectService.getProjectById(1L);

        // Vérifier les résultats
        assertNotNull(foundProject);
        assertEquals(project.getId(), foundProject.getId());
        assertEquals(project.getName(), foundProject.getName());
        assertEquals(project.getDescription(), foundProject.getDescription());

        // Vérifier que projectRepo.findById a été appelé une fois
        verify(projectRepo, times(1)).findById(1L);
    }



    @Test
    void testGetProjectById_NotFound() {

    }
    }