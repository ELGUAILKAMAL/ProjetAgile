package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.SprintBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.DTO.TaskDTO;
import com.gestionprojetagile.ProjetAgile.web.DTO.UserStoryDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.SprintBacklog;
import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
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

    private ISprintBacklog sprintBacklogService;
    public SprintBacklogController(ISprintBacklog sprintBacklogService)
    {this.sprintBacklogService=sprintBacklogService;}
    @PostMapping
    public ResponseEntity<SprintBacklogDTO> createSprintBacklog(@RequestBody SprintBacklogDTO sprintBacklogDTO) {
        SprintBacklog sprintBacklog = convertToEntity(sprintBacklogDTO);
        SprintBacklog createdSprint = sprintBacklogService.createSprintBacklog(sprintBacklog);
        return new ResponseEntity<>(convertToDTO(createdSprint), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintBacklogDTO> getSprintBacklogById(@PathVariable Long id) {
        SprintBacklog sprintBacklog = sprintBacklogService.getSprintBacklogById(id);
        return ResponseEntity.ok(convertToDTO(sprintBacklog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintBacklogDTO> updateSprintBacklog(
            @PathVariable Long id,
            @RequestBody SprintBacklogDTO sprintBacklogDTO) {
        SprintBacklog sprintBacklog = convertToEntity(sprintBacklogDTO);
        SprintBacklog updatedSprint = sprintBacklogService.updateSprintBacklog(id, sprintBacklog);
        return ResponseEntity.ok(convertToDTO(updatedSprint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprintBacklog(@PathVariable Long id) {
        sprintBacklogService.deleteSprintBacklog(id);
        return ResponseEntity.noContent().build();
    }

    // User Story Management
    @PostMapping("/{id}/user-stories")
    public ResponseEntity<Void> addUserStoryToSprint(
            @PathVariable Long id,
            @RequestBody UserStoryDTO userStoryDTO) {
        UserStory userStory = convertToUserStoryEntity(userStoryDTO);
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
        Task task = convertToTaskEntity(taskDTO);
        Task createdTask = sprintBacklogService.addTaskToUserStoryInSprintBacklog(userStoryId, task);
        return new ResponseEntity<>(convertToTaskDTO(createdTask), HttpStatus.CREATED);
    }

    @DeleteMapping("/{sprintId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTaskFromSprint(
            @PathVariable Long sprintId,
            @PathVariable Long taskId) {
        sprintBacklogService.deleteTasksFromUserStory(sprintId, taskId);
        return ResponseEntity.noContent().build();
    }

    private SprintBacklog convertToEntity(SprintBacklogDTO dto) {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setId(dto.getId());
        sprintBacklog.setName(dto.getName());
        sprintBacklog.setStartDate(dto.getStartDate());
        sprintBacklog.setEndDate(dto.getEndDate());
        return sprintBacklog;
    }

    private SprintBacklogDTO convertToDTO(SprintBacklog sprintBacklog) {
        return new SprintBacklogDTO(
                sprintBacklog.getId(),
                sprintBacklog.getName(),
                sprintBacklog.getStartDate(),
                sprintBacklog.getEndDate()
        );
    }

    private UserStory convertToUserStoryEntity(UserStoryDTO dto) {
        UserStory userStory = new UserStory();
        userStory.setId(dto.getId());
        return userStory;
    }

    private Task convertToTaskEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        return task;
    }

    private TaskDTO convertToTaskDTO(Task task) {
        return new TaskDTO(task.getId());
    }
}