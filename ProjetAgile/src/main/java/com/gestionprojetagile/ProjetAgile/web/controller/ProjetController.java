package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.ProjectDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Project;
import com.gestionprojetagile.ProjetAgile.web.mapping.ProjectMapper;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjetController {
    private ProjectMapper projectMapper;
    private IProject projectService;
    public ProjetController(IProject projectService, ProjectMapper projectMapper)
    {
        this.projectMapper=projectMapper;
        this.projectService=projectService;
    }
    @PostMapping("/createProject")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = projectMapper.projectDtoToProject(projectDTO);
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(projectMapper.projectToProjectDto(createdProject), HttpStatus.CREATED);
    }

    @GetMapping("/getProject/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(projectMapper.projectToProjectDto(project));
    }

    @GetMapping("/getProjects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for(Project project : projects){
            projectDTOs.add(projectMapper.projectToProjectDto(project));
        }
        return ResponseEntity.ok(projectDTOs);
    }

    @PutMapping("/updateProject/{id}")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectDTO projectDTO) {
        Project project = projectMapper.projectDtoToProject(projectDTO);
        Project updatedProject = projectService.updateProject(id, project);
        return ResponseEntity.ok(projectMapper.projectToProjectDto(updatedProject));
    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }


}