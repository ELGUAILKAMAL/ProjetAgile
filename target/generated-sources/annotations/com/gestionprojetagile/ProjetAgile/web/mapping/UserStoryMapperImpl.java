package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.UserStoryDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T14:39:33+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserStoryMapperImpl implements UserStoryMapper {

    @Override
    public UserStoryDTO userStoryToUserStoryDto(UserStory userStory) {
        if ( userStory == null ) {
            return null;
        }

        UserStoryDTO userStoryDTO = new UserStoryDTO();

        userStoryDTO.setId( userStory.getId() );
        userStoryDTO.setTitle( userStory.getTitle() );
        userStoryDTO.setEntanque( userStory.getEntanque() );
        userStoryDTO.setJesouhaite( userStory.getJesouhaite() );
        userStoryDTO.setAfinde( userStory.getAfinde() );
        userStoryDTO.setPriority( userStory.getPriority() );
        userStoryDTO.setStatus( userStory.getStatus() );

        return userStoryDTO;
    }

    @Override
    public UserStory userStoryDtoToUserStory(UserStoryDTO userStoryDTO) {
        if ( userStoryDTO == null ) {
            return null;
        }

        UserStory userStory = new UserStory();

        userStory.setId( userStoryDTO.getId() );
        userStory.setTitle( userStoryDTO.getTitle() );
        userStory.setEntanque( userStoryDTO.getEntanque() );
        userStory.setJesouhaite( userStoryDTO.getJesouhaite() );
        userStory.setAfinde( userStoryDTO.getAfinde() );
        userStory.setPriority( userStoryDTO.getPriority() );
        userStory.setStatus( userStoryDTO.getStatus() );

        return userStory;
    }
}
