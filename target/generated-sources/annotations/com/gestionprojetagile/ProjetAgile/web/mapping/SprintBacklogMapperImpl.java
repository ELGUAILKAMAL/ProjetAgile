package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.SprintBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T14:39:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class SprintBacklogMapperImpl implements SprintBacklogMapper {

    @Override
    public SprintBacklogDTO sprintBlToSpringBlDto(SprintBacklog sprintBacklogDTO) {
        if ( sprintBacklogDTO == null ) {
            return null;
        }

        SprintBacklogDTO sprintBacklogDTO1 = new SprintBacklogDTO();

        sprintBacklogDTO1.setId( sprintBacklogDTO.getId() );
        sprintBacklogDTO1.setName( sprintBacklogDTO.getName() );
        sprintBacklogDTO1.setStartDate( sprintBacklogDTO.getStartDate() );
        sprintBacklogDTO1.setEndDate( sprintBacklogDTO.getEndDate() );

        return sprintBacklogDTO1;
    }

    @Override
    public SprintBacklog sprintBlDtoToSprintBl(SprintBacklogDTO sprintBacklogDTO) {
        if ( sprintBacklogDTO == null ) {
            return null;
        }

        SprintBacklog sprintBacklog = new SprintBacklog();

        sprintBacklog.setId( sprintBacklogDTO.getId() );
        sprintBacklog.setName( sprintBacklogDTO.getName() );
        sprintBacklog.setStartDate( sprintBacklogDTO.getStartDate() );
        sprintBacklog.setEndDate( sprintBacklogDTO.getEndDate() );

        return sprintBacklog;
    }
}
