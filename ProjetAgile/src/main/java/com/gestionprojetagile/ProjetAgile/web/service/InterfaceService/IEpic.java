package com.gestionprojetagile.ProjetAgile.web.service.InterfaceService;



import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;

import java.util.Collection;
import java.util.List;

public interface IEpic {
    Epic createEpic(Epic epic);
    Epic getEpicById(Long id);
    List<Epic> getAllEpics();
    Epic updateEpic(Long id, Epic epic);
    void deleteEpic(Long id);
    Collection<UserStory> getUserStoriesByEpicId(Long epicId);
    List<UserStory> decouperEpic(Long id, List<UserStory> userStories);
    void deleteUserStoryEpic(Long id, Long userStoryId);
}
