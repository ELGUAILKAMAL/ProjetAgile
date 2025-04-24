package com.gestionprojetagile.ProjetAgile.web.mapping;


import com.gestionprojetagile.ProjetAgile.web.DTO.ProjectDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO projectToProjectDto(Project project);

    Project projectDtoToProject(ProjectDTO projectDTO);
}
