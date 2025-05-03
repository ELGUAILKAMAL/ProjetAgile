package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.SprintBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SprintBacklogMapper {
    SprintBacklogDTO sprintBlToSpringBlDto(SprintBacklog sprintBacklogDTO);

    SprintBacklog sprintBlDtoToSprintBl(SprintBacklogDTO sprintBacklogDTO);
}
