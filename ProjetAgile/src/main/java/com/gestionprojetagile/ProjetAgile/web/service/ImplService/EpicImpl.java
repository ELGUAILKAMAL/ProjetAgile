package com.gestionprojetagile.ProjetAgile.web.service.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.exceptions.EpicNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.repositories.EpicRepo;
import com.gestionprojetagile.ProjetAgile.web.repositories.UserStoryRepo;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IEpic;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class EpicImpl implements IEpic {
    private final EpicRepo epicRepo;
    private final UserStoryRepo userStoryRepo;
    public EpicImpl(EpicRepo epicRepo, UserStoryRepo userStoryRepo) {
        this.epicRepo = epicRepo;
        this.userStoryRepo=userStoryRepo;
    }
    @Override
    public Epic createEpic(Epic epic){
        return epicRepo.save(epic);
    }
    @Override
    public Epic getEpicById(Long id){
        Optional<Epic> epicOptional = epicRepo.findById(id);
        if(epicOptional.isPresent()){
            return epicOptional.get();
        }
        else{
            throw new EpicNotFoundException("Cette epic n'existe pas!");
        }
    }

    @Override
    public List<Epic> getAllEpics(){
        return epicRepo.findAll();
    }
    @Override
    public Epic updateEpic(Long id, Epic epic){
        Optional<Epic> epicOptional = epicRepo.findById(id);
        if(epicOptional.isPresent()){
            Epic ourEpic = epicOptional.get();
            ourEpic.setName(epic.getName());
            ourEpic.setDescription(epic.getDescription());
            return epicRepo.save(ourEpic);
        }
        else{
            throw new EpicNotFoundException("Cette epic n'existe pas!");
        }
    }
    @Override
    public void deleteEpic(Long id){
        Optional<Epic> epicOptional = epicRepo.findById(id);
        if(epicOptional.isPresent()){
            epicRepo.delete(epicOptional.get());
        }
        else{
            throw new EpicNotFoundException("Cette epic n'existe pas!");
        }
    }
    @Override
    public Collection<UserStory> getUserStoriesByEpicId(Long id){
        Optional<Epic> epicOptional = epicRepo.findById(id);
        if(epicOptional.isPresent()){
            return epicOptional.get().getUserStories();
        }
        else{
            throw new EpicNotFoundException("Cette epic n'existe pas!");
        }
    }

    @Override
    public List<UserStory> decouperEpic(Long id, List<UserStory> userStories) {
        Optional<Epic> epicOptional = epicRepo.findById(id);
        if(epicOptional.isPresent()){
            for(UserStory userStory : userStories){
                epicOptional.get().getUserStories().add(userStory);
            }
            return userStories;
        }
        else{
            throw new EpicNotFoundException("Cette epic n'existe pas!");
        }
    }

    @Override
    public void deleteUserStoryEpic(Long id, Long userStoryId) {
        Optional<Epic> epicOptional = epicRepo.findById(id);
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(userStoryId);
        if(epicOptional.isPresent() && userStoryOptional.isPresent()){
            epicOptional.get().getUserStories().remove(userStoryOptional.get());
        }
        else{
            throw new EpicNotFoundException("l'UserStory ou l'Epic n'existe pas!");
        }
    }
}
