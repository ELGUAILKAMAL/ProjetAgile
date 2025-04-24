package com.gestionprojetagile.ProjetAgile.web.service.InterfaceService;


import com.gestionprojetagile.ProjetAgile.web.Enities.Task;

import java.util.List;

public interface ITask {
    Task createTask(Long userStoryId, Task task);
    Task getTaskById(Long id);
    List<Task> getTasksByUserStoryId(Long userStoryId);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    Task updateTaskStatus(Long taskId, String status);
    List<Task> getTasksByAssignedUserId(Long userId);

}
