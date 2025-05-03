package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.UserStoryDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.mapping.UserStoryMapper;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IUserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {

    private IUserStory userStoryService;
    private UserStoryMapper userStoryMapper;
    public UserStoryController(IUserStory userStoryService, UserStoryMapper userStoryMapper){
        this.userStoryMapper=userStoryMapper;
        this.userStoryService=userStoryService;
    }
    @PostMapping("/createUserStory")
    public ResponseEntity<UserStoryDTO> createUserStory(@RequestBody UserStoryDTO userStoryDTO) {
        UserStory userStory = userStoryMapper.userStoryDtoToUserStory(userStoryDTO);
        UserStory createdUserStory = userStoryService.createUserStory(userStory);
        return new ResponseEntity<>(userStoryMapper.userStoryToUserStoryDto(createdUserStory), HttpStatus.CREATED);
    }

    @GetMapping("/getUserStory{id}")
    public ResponseEntity<UserStoryDTO> getUserStoryById(@PathVariable Long id) {
        UserStory userStory = userStoryService.getUserStoryById(id);
        return ResponseEntity.ok(userStoryMapper.userStoryToUserStoryDto(userStory));
    }

    @GetMapping("/getUserStories")
    public ResponseEntity<List<UserStoryDTO>> getAllUserStories() {
        List<UserStory> userStories = userStoryService.getAllUserStories();
        List<UserStoryDTO> userStoryDTOS = new ArrayList<>();
        for(UserStory userStory : userStories){
            userStoryDTOS.add(userStoryMapper.userStoryToUserStoryDto(userStory));
        }
        return ResponseEntity.ok(userStoryDTOS);
    }

    @PutMapping("/modifierUserStory/{id}")
    public ResponseEntity<UserStoryDTO> updateUserStory(
            @PathVariable Long id,
            @RequestBody UserStoryDTO userStoryDTO) {
        UserStory userStory = userStoryMapper.userStoryDtoToUserStory(userStoryDTO);
        UserStory updatedUserStory = userStoryService.updateUserStory(id, userStory);
        return ResponseEntity.ok(userStoryMapper.userStoryToUserStoryDto(updatedUserStory));
    }

    @DeleteMapping("/supprimerUserStory/{id}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable Long id) {
        userStoryService.deleteUserStory(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UserStoryDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        UserStory userStory = userStoryService.updateUserStoryStatus(id, status);
        return ResponseEntity.ok(userStoryMapper.userStoryToUserStoryDto(userStory));
    }

    @GetMapping("/product-backlog/{productBacklogId}")
    public ResponseEntity<List<UserStoryDTO>> getByProductBacklog(
            @PathVariable Long productBacklogId) {
        Collection<UserStory> userStories = userStoryService.getUserStoriesByProductBacklogId(productBacklogId);
        List<UserStoryDTO> dtos = new ArrayList<>();
        for(UserStory story : userStories){
            dtos.add(userStoryMapper.userStoryToUserStoryDto(story));
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<List<UserStoryDTO>> getByEpic(
            @PathVariable Long epicId) {
        Collection<UserStory> userStories = userStoryService.getUserStoriesByEpicId(epicId);
        List<UserStoryDTO> dtos = new ArrayList<>();
        for(UserStory story : userStories){
            dtos.add(userStoryMapper.userStoryToUserStoryDto(story));
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/product-backlog/{id}/prioritize")
    public ResponseEntity<Void> prioritizeProductBacklogStories(
            @PathVariable long id) {
        userStoryService.prioritizeUserStoriesProdBl(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sprint-backlog/{id}/prioritize")
    public ResponseEntity<Void> prioritizeSprintBacklogStories(
            @PathVariable long id) {
        userStoryService.prioritizeUserStoriesSprintBl(id);
        return ResponseEntity.ok().build();
    }


}