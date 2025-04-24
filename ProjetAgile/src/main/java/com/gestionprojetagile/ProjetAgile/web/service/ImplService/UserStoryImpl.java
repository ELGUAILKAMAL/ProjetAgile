package com.gestionprojetagile.ProjetAgile.web.service.ImplService;


import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.exceptions.EpicNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.exceptions.ProductBlNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.exceptions.SprintBlNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.exceptions.UserStoryNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.repositories.EpicRepo;
import com.gestionprojetagile.ProjetAgile.web.repositories.ProdBacklogRepo;
import com.gestionprojetagile.ProjetAgile.web.repositories.SprintBacklogRepo;
import com.gestionprojetagile.ProjetAgile.web.repositories.UserStoryRepo;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IUserStory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserStoryImpl implements IUserStory {

    private final UserStoryRepo userStoryRepo;
    private final SprintBacklogRepo sprintBacklogRepo;
    private final ProdBacklogRepo prodBacklogRepo;
    private final EpicRepo epicRepo;
    public UserStoryImpl(UserStoryRepo userStoryRepo, SprintBacklogRepo sprintBacklogRepo,
                         ProdBacklogRepo prodBacklogRepo, EpicRepo epicRepo ) {
        this.userStoryRepo = userStoryRepo;
        this.prodBacklogRepo= prodBacklogRepo;
        this.sprintBacklogRepo= sprintBacklogRepo;
        this.epicRepo=epicRepo;
    }
    @Override
    public UserStory createUserStory(UserStory userStory) {
        userStoryRepo.save(userStory);
        return userStory;
    }

    @Override
    public UserStory getUserStoryById(Long id) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(id);
        return userStoryOptional.isPresent() ? userStoryOptional.get() : null;
    }

    @Override
    public UserStory updateUserStory(Long id, UserStory userStory) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(id);
        if(userStoryOptional.isPresent()){
            userStoryRepo.save(userStory);
            return  userStory;
        }
        else{
            throw new UserStoryNotFoundException("User Story n'existe pas ");
        }
    }

    @Override
    public List<UserStory> getAllUserStories() {
        return userStoryRepo.findAll();
    }
    @Override
    public void deleteUserStory(Long id) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(id);
        if(userStoryOptional.isPresent()){
            userStoryRepo.delete(userStoryOptional.get());
        }
        else{
            throw new UserStoryNotFoundException("User Story n'existe pas ");
        }
    }

    @Override
    public UserStory updateUserStoryStatus(Long userStoryId, String status) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(userStoryId);
        if(userStoryOptional.isPresent()){
            userStoryOptional.get().setStatus(status);
            return userStoryOptional.get();
        }
        else{
            throw new UserStoryNotFoundException("User Story n'existe pas ");
        }
    }

    @Override
    public Collection<UserStory> getUserStoriesByProductBacklogId(Long productBacklogId) {
        Optional<ProductBacklog> productBacklogOptional = prodBacklogRepo.findById(productBacklogId);
        if(productBacklogOptional.isPresent()){
            return productBacklogOptional.get().getUserStories();
        }
        else{
            throw new ProductBlNotFoundException("Cette product backlog n'existe pas");
        }
    }

    @Override
    public Collection<UserStory> getUserStoriesByEpicId(Long epicId) {
        Optional<Epic> epicOptional = epicRepo.findById(epicId);
        if(epicOptional.isPresent()){
            return epicOptional.get().getUserStories();
        }
        else{
            throw new EpicNotFoundException("Cette epic n'existe pas");
        }
    }

    @Override
    public void prioritizeUserStoriesProdBl(long id) {
        Optional<ProductBacklog> productBacklogOptional = prodBacklogRepo.findById(id);
        if(productBacklogOptional.isPresent()){
            List<UserStory> userStoriesPrioritized = userStoryRepo.findAllByProductBacklogOrderByPriority(productBacklogOptional.get());
            productBacklogOptional.get().setUserStories(userStoriesPrioritized);
        }
        else{
            throw new ProductBlNotFoundException("Cette product backlog n'existe pas");
        }
    }

    @Override
    public void prioritizeUserStoriesSprintBl(long id) {
        Optional<SprintBacklog> sprintBacklogOptional = sprintBacklogRepo.findById(id);
        if(sprintBacklogOptional.isPresent()){
            List<UserStory> userStoriesPrioritized = userStoryRepo.findAllBySprintBacklogOrderByPriority(sprintBacklogOptional.get());
            sprintBacklogOptional.get().setUserstories(userStoriesPrioritized);
        }
        else{
            throw new SprintBlNotFoundException("Cette Sprint backlog n'existe pas");
        }
    }

}
