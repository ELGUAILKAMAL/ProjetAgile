package com.gestionprojetagile.ProjetAgile.web.service.InterfaceService;


import com.gestionprojetagile.ProjetAgile.web.Enities.Project;

import java.util.List;

public interface IProject {
        Project createProject(Project project);
        Project getProjectById(Long id);
        List<Project> getAllProjects();
        Project updateProject(Long id, Project project);
        void deleteProject(Long id);

}
