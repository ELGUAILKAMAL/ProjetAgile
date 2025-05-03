package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.EpicDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EpicMapper {
    EpicDTO epicToEpicDto(Epic epic);
    Epic epicDtoToEpic(EpicDTO epicDTO);
}
