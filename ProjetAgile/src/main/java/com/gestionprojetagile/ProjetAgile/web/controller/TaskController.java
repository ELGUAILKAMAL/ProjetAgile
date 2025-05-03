package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.TaskDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.mapping.TaskMapper;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.ITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskMapper taskMapper;
    private ITask taskService;
    public TaskController(ITask iTask, TaskMapper taskMapper){
        this.taskService=iTask;
        this.taskMapper=taskMapper;
    }
    // CRUD Operations
    @PostMapping("/user-story/{userStoryId}")
    public ResponseEntity<TaskDTO> createTask(
            @PathVariable Long userStoryId,
            @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.taskDtoToTask(taskDTO);
        Task createdTask = taskService.createTask(userStoryId, task);
        return new ResponseEntity<>(taskMapper.taskToTaskDto(createdTask), HttpStatus.CREATED);
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(taskMapper.taskToTaskDto(task));
    }

    @GetMapping("/user-story/{userStoryId}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserStory(
            @PathVariable Long userStoryId) {
        List<Task> tasks = taskService.getTasksByUserStoryId(userStoryId);
        List<TaskDTO> dtos = new ArrayList<>();
        for(Task task : tasks){
            dtos.add(taskMapper.taskToTaskDto(task));
        }
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/modifyTask/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.taskDtoToTask(taskDTO);
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(taskMapper.taskToTaskDto(updatedTask));
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Status Management
    @PatchMapping("/modifyStatus/{id}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Task task = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(taskMapper.taskToTaskDto(task));
    }

    // User Assignment
    @GetMapping("/assigned-to/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByAssignee(
            @PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByAssignedUserId(userId);
        List<TaskDTO> dtos = new ArrayList<>();
        for(Task task : tasks){
            dtos.add(taskMapper.taskToTaskDto(task));
        }
        return ResponseEntity.ok(dtos);
    }


}