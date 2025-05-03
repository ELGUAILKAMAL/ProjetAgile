package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.SprintBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.DTO.TaskDTO;
import com.gestionprojetagile.ProjetAgile.web.DTO.UserStoryDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.mapping.SprintBacklogMapper;
import com.gestionprojetagile.ProjetAgile.web.mapping.TaskMapper;
import com.gestionprojetagile.ProjetAgile.web.mapping.UserStoryMapper;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.ISprintBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sprint-backlogs")
public class SprintBacklogController {
    private SprintBacklogMapper sprintBacklogMapper;
    private TaskMapper taskMapper;
    private UserStoryMapper userStoryMapper;
    private ISprintBacklog sprintBacklogService;
    public SprintBacklogController(ISprintBacklog sprintBacklogService,UserStoryMapper userStoryMapper,TaskMapper taskMapper, SprintBacklogMapper sprintBacklogMapper)
    {this.sprintBacklogService=sprintBacklogService;
        this.taskMapper=taskMapper;
        this.userStoryMapper=userStoryMapper;
    this.sprintBacklogMapper=sprintBacklogMapper;}
    @PostMapping("/createSpringBacklog")
    public ResponseEntity<SprintBacklogDTO> createSprintBacklog(@RequestBody SprintBacklogDTO sprintBacklogDTO) {
        SprintBacklog sprintBacklog = sprintBacklogMapper.sprintBlDtoToSprintBl(sprintBacklogDTO);
        SprintBacklog createdSprint = sprintBacklogService.createSprintBacklog(sprintBacklog);
        return new ResponseEntity<>(sprintBacklogMapper.sprintBlToSpringBlDto(createdSprint), HttpStatus.CREATED);
    }

    @GetMapping("/getSprintBacklog/{id}")
    public ResponseEntity<SprintBacklogDTO> getSprintBacklogById(@PathVariable Long id) {
        SprintBacklog sprintBacklog = sprintBacklogService.getSprintBacklogById(id);
        return ResponseEntity.ok(sprintBacklogMapper.sprintBlToSpringBlDto(sprintBacklog));
    }

    @PutMapping("/modifySprintBacklog/{id}")
    public ResponseEntity<SprintBacklogDTO> updateSprintBacklog(
            @PathVariable Long id,
            @RequestBody SprintBacklogDTO sprintBacklogDTO) {
        SprintBacklog sprintBacklog = sprintBacklogMapper.sprintBlDtoToSprintBl(sprintBacklogDTO);
        SprintBacklog updatedSprint = sprintBacklogService.updateSprintBacklog(id, sprintBacklog);
        return ResponseEntity.ok(sprintBacklogMapper.sprintBlToSpringBlDto(updatedSprint));
    }

    @DeleteMapping("/deleteSprintBacklog/{id}")
    public ResponseEntity<Void> deleteSprintBacklog(@PathVariable Long id) {
        sprintBacklogService.deleteSprintBacklog(id);
        return ResponseEntity.noContent().build();
    }

    // User Story Management
    @PostMapping("/{id}/user-stories")
    public ResponseEntity<Void> addUserStoryToSprint(
            @PathVariable Long id,
            @RequestBody UserStoryDTO userStoryDTO) {
        UserStory userStory = userStoryMapper.userStoryDtoToUserStory(userStoryDTO);
        sprintBacklogService.addUserStoriesToSprintBl(id, userStory);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{sprintId}/user-stories/{userStoryId}")
    public ResponseEntity<Void> removeUserStoryFromSprint(
            @PathVariable Long sprintId,
            @PathVariable Long userStoryId) {
        sprintBacklogService.removeUserStoryFromSprintBacklog(sprintId, userStoryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user-stories/{userStoryId}/tasks")
    public ResponseEntity<TaskDTO> addTaskToUserStory(
            @PathVariable Long userStoryId,
            @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.taskDtoToTask(taskDTO);
        Task createdTask = sprintBacklogService.addTaskToUserStoryInSprintBacklog(userStoryId, task);
        return new ResponseEntity<>(taskMapper.taskToTaskDto(createdTask), HttpStatus.CREATED);
    }

    @DeleteMapping("/{sprintId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTaskFromSprint(
            @PathVariable Long sprintId,
            @PathVariable Long taskId) {
        sprintBacklogService.deleteTasksFromUserStory(sprintId, taskId);
        return ResponseEntity.noContent().build();
    }



}