package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.ProjectDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Project;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjetController {

    private IProject projectService;
    public ProjetController(IProject projectService)
    {
        this.projectService=projectService;
    }
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(convertToDTO(createdProject), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(convertToDTO(project));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project updatedProject = projectService.updateProject(id, project);
        return ResponseEntity.ok(convertToDTO(updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    private Project convertToEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        return project;
    }

    private ProjectDTO convertToDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription()
        );
    }
}