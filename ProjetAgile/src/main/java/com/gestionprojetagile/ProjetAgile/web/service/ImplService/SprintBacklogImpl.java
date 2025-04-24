package com.gestionprojetagile.ProjetAgile.web.service.ImplService;


import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.exceptions.SprintBlNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.exceptions.UserStoryNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.repositories.SprintBacklogRepo;
import com.gestionprojetagile.ProjetAgile.web.repositories.UserStoryRepo;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.ISprintBacklog;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SprintBacklogImpl implements ISprintBacklog {

    private final SprintBacklogRepo sprintBacklogRepo;
    private final UserStoryRepo userStoryRepo;
    public SprintBacklogImpl(SprintBacklogRepo sprintBacklogRepo , UserStoryRepo userStoryRepo) {
        this.sprintBacklogRepo = sprintBacklogRepo;
        this.userStoryRepo = userStoryRepo;
    }
    @Override
    public SprintBacklog createSprintBacklog(SprintBacklog sprintBacklog) {
        sprintBacklogRepo.save(sprintBacklog);
        return sprintBacklog;
    }

    @Override
    public SprintBacklog updateSprintBacklog(Long id, SprintBacklog sprintBacklog) {
        Optional<SprintBacklog> sprintBacklogOptional = sprintBacklogRepo.findById(id);
        if(sprintBacklogOptional.isPresent()){
            sprintBacklogRepo.save(sprintBacklog);
            return sprintBacklog;
        }
        else{
            throw new SprintBlNotFoundException("Sprint backlog n'est pas trouvée");
        }
    }

    @Override
    public SprintBacklog getSprintBacklogById(Long id) {
        Optional<SprintBacklog> sprintBacklogOptional = sprintBacklogRepo.findById(id);
        if(sprintBacklogOptional.isPresent()){
            return sprintBacklogOptional.get();
        }
        else{
            throw new SprintBlNotFoundException("Sprint backlog n'existe pas");
        }
    }

    @Override
    public void deleteSprintBacklog(Long id) {
        Optional<SprintBacklog> sprintBacklogOptional = sprintBacklogRepo.findById(id);
        if(sprintBacklogOptional.isPresent()){
            sprintBacklogRepo.delete(sprintBacklogOptional.get());
        }
        else{
            throw new SprintBlNotFoundException("Sprint backlog n'existe pas");
        }
    }

    @Override
    public void addUserStoriesToSprintBl(Long id, UserStory userstory) {
        Optional<SprintBacklog> sprintBacklogOptional = sprintBacklogRepo.findById(id);
        if(sprintBacklogOptional.isPresent()){
            sprintBacklogOptional.get().getUserstories().add(userstory);
        }
        else{
            throw new SprintBlNotFoundException("Sprint backlog n'existe pas");
        }
    }

    @Override
    public void removeUserStoryFromSprintBacklog(Long sprintBacklogId, Long userStoryId) {
        Optional<SprintBacklog> sprintBacklogOptional = sprintBacklogRepo.findById(sprintBacklogId);
        if(sprintBacklogOptional.isPresent()){
            sprintBacklogOptional.get().getUserstories().removeIf(userStory -> userStory.getId().equals(userStoryId));
        }
        else{
            throw new SprintBlNotFoundException("Sprint backlog n'existe pas");
        }
    }
    @Override
    public void deleteTasksFromUserStory(Long userStoryId, Long taskId) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(userStoryId);
        if(userStoryOptional.isPresent()){
            userStoryOptional.get().getTasks().removeIf(task-> task.getId().equals(taskId));
        }
        else{
            throw new UserStoryNotFoundException("User Story n'existe pas");
        }
    }

    @Override
    public Task addTaskToUserStoryInSprintBacklog(Long userStoryId, Task task) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(userStoryId);
        if( userStoryOptional.isPresent()){
            userStoryOptional.get().getTasks().add(task);
            return task;
        }
        else{
            throw new UserStoryNotFoundException("UserStory n'existe pas");
        }
    }
}
