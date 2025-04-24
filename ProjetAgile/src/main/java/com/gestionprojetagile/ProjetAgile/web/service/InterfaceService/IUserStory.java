package com.gestionprojetagile.ProjetAgile.web.service.InterfaceService;

import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public interface IUserStory {
    UserStory createUserStory(UserStory userStory);

    UserStory getUserStoryById(Long id);

    UserStory updateUserStory(Long id, UserStory userStory);

    List<UserStory> getAllUserStories();

    void deleteUserStory(Long id);

    UserStory updateUserStoryStatus(Long userStoryId, String status);

    Collection<UserStory> getUserStoriesByProductBacklogId(Long productBacklogId);

    Collection<UserStory> getUserStoriesByEpicId(Long epicId);

    void prioritizeUserStoriesProdBl(long id);

    void prioritizeUserStoriesSprintBl(long id);
//     // Gestion des User Story
//        UserStory createUserStory(UserStory userStory);
//        UserStory getUserStoryById(Long id);
//        List<UserStory> getAllUserStories();
//        UserStory updateUserStory(Long id, UserStory userStory);
//        void deleteUserStory(Long id);
//
//        // Lier une User Story à un Epic
//        UserStory linkUserStoryToEpic(Long userStoryId, Long epicId);
//        // Dissocier une User Story d'un Epic
//        UserStory unlinkUserStoryFromEpic(Long userStoryId);
//        // Définir les critères d'acceptation pour une User Story
//        UserStory setAcceptanceCriteria(Long userStoryId, String acceptanceCriteria);
//        // Mettre à jour le statut d'une User Story (To Do, In Progress, Done)
//        UserStory updateUserStoryStatus(Long userStoryId, String status);
//        // Prioriser les User Stories dans le Product Backlog
//        void prioritizeUserStories(List<Long> userStoryIdsInOrder);
//        // Récupérer toutes les User Stories d'un Product Backlog
//        List<UserStory> getUserStoriesByProductBacklogId(Long productBacklogId);
//        // Récupérer toutes les User Stories liées à un Epic
//        List<UserStory> getUserStoriesByEpicId(Long epicId);
//        // Récupérer toutes les User Stories non liées à un Epic
//        List<UserStory> getUnlinkedUserStories();

}
