package com.gestionprojetagile.ProjetAgile.web.service.InterfaceService;


import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;

public interface ISprintBacklog {
    SprintBacklog createSprintBacklog(SprintBacklog sprintBacklog);
    SprintBacklog getSprintBacklogById(Long id);
    SprintBacklog updateSprintBacklog(Long id, SprintBacklog sprintBacklog);
    void deleteSprintBacklog(Long id);
    void addUserStoriesToSprintBl(Long id, UserStory userstory);
    void removeUserStoryFromSprintBacklog(Long sprintBacklogId, Long userStoryId);
    Task addTaskToUserStoryInSprintBacklog(Long userStoryId, Task task);
    void deleteTasksFromUserStory(Long sprintBacklogId, Long taskId);
}
