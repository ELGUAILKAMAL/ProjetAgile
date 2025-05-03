package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.EpicDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T14:39:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class EpicMapperImpl implements EpicMapper {

    @Override
    public EpicDTO epicToEpicDto(Epic epic) {
        if ( epic == null ) {
            return null;
        }

        EpicDTO epicDTO = new EpicDTO();

        epicDTO.setId( epic.getId() );
        epicDTO.setName( epic.getName() );
        epicDTO.setDescription( epic.getDescription() );

        return epicDTO;
    }

    @Override
    public Epic epicDtoToEpic(EpicDTO epicDTO) {
        if ( epicDTO == null ) {
            return null;
        }

        Epic epic = new Epic();

        epic.setId( epicDTO.getId() );
        epic.setName( epicDTO.getName() );
        epic.setDescription( epicDTO.getDescription() );

        return epic;
    }
}
