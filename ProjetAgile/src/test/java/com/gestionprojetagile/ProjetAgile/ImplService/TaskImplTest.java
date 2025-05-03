package com.gestionprojetagile.ProjetAgile.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.*;
import com.gestionprojetagile.ProjetAgile.web.exceptions.*;
import com.gestionprojetagile.ProjetAgile.web.repositories.*;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.TaskImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskImplTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private UserStoryRepo userStoryRepo;

    @Mock
    private UtilisateurRepo utilisateurRepo;

    @InjectMocks
    private TaskImpl taskService;

    private Task task;
    private UserStory userStory;
    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setDescription("Test Task");

        userStory = new UserStory();
        userStory.setId(1L);

        utilisateur = new Utilisateur();
        utilisateur.setId(1L);
    }

    @Test
    void createTask_WhenUserStoryExists_ShouldReturnTask() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));
        when(taskRepo.save(any(Task.class))).thenReturn(task);

        Task result = taskService.createTask(1L, task);

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
        verify(taskRepo, times(1)).save(task);
    }

    @Test
    void createTask_WhenUserStoryNotExists_ShouldThrowException() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserStoryNotFoundException.class, () -> {
            taskService.createTask(1L, task);
        });
    }

    @Test
    void getTaskById_WhenExists_ShouldReturnTask() {
        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
    }

    @Test
    void getTaskById_WhenNotExists_ShouldThrowException() {
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTaskById(1L);
        });
    }

    @Test
    void updateTask_WhenExists_ShouldReturnUpdatedTask() {
        when(taskRepo.existsById(1L)).thenReturn(true);
        when(taskRepo.save(any(Task.class))).thenReturn(task);

        Task updated = new Task();
        updated.setDescription("Updated Task");

        Task result = taskService.updateTask(1L, updated);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskRepo, times(1)).save(updated);
    }

    @Test
    void updateTask_WhenNotExists_ShouldThrowException() {
        when(taskRepo.existsById(1L)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.updateTask(1L, new Task());
        });
    }

    @Test
    void deleteTask_WhenExists_ShouldDelete() {
        when(taskRepo.existsById(1L)).thenReturn(true);
        doNothing().when(taskRepo).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepo, times(1)).deleteById(1L);
    }

    @Test
    void deleteTask_WhenNotExists_ShouldThrowException() {
        when(taskRepo.existsById(1L)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.deleteTask(1L);
        });
    }

    @Test
    void getTasksByUserStoryId_WhenExists_ShouldReturnList() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));
        when(taskRepo.findTaskByUserStory(userStory)).thenReturn(Arrays.asList(task));

        List<Task> result = taskService.getTasksByUserStoryId(1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void updateTaskStatus_WhenExists_ShouldUpdateStatus() {
        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepo.save(any(Task.class))).thenReturn(task);

        Task result = taskService.updateTaskStatus(1L, "Done");

        assertNotNull(result);
        assertEquals("Done", result.getStatus());
    }

    @Test
    void getTasksByAssignedUserId_WhenExists_ShouldReturnList() {
        when(utilisateurRepo.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(taskRepo.findTaskByUtilisateur(utilisateur)).thenReturn(Arrays.asList(task));

        List<Task> result = taskService.getTasksByAssignedUserId(1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void getTasksByAssignedUserId_WhenNotExists_ShouldThrowException() {
        when(utilisateurRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            taskService.getTasksByAssignedUserId(1L);
        });
    }
}