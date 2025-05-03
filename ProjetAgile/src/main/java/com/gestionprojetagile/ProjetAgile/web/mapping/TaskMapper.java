package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.TaskDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO taskToTaskDto(Task task);

    Task taskDtoToTask(TaskDTO taskDTO);
}
