package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.TaskDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.ITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private ITask taskService;
    public TaskController(ITask iTask){
        this.taskService=iTask;
    }
    // CRUD Operations
    @PostMapping("/user-story/{userStoryId}")
    public ResponseEntity<TaskDTO> createTask(
            @PathVariable Long userStoryId,
            @RequestBody TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task createdTask = taskService.createTask(userStoryId, task);
        return new ResponseEntity<>(convertToDTO(createdTask), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(convertToDTO(task));
    }

    @GetMapping("/user-story/{userStoryId}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserStory(
            @PathVariable Long userStoryId) {
        List<Task> tasks = taskService.getTasksByUserStoryId(userStoryId);
        List<TaskDTO> dtos = tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(convertToDTO(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Status Management
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Task task = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(convertToDTO(task));
    }

    // User Assignment
    @GetMapping("/assigned-to/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByAssignee(
            @PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByAssignedUserId(userId);
        List<TaskDTO> dtos = tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Conversion Methods
    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        return task;
    }

    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
}