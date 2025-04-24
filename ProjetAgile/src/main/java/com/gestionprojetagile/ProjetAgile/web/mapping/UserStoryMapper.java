package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.UserStoryDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserStoryMapper {
    UserStoryMapper INSTANCE = Mappers.getMapper(UserStoryMapper.class);

    UserStoryDTO userStoryToUserStoryDto(UserStory userStory);

    UserStory userStoryDtoToUserStory(UserStoryDTO userStoryDTO);
}
