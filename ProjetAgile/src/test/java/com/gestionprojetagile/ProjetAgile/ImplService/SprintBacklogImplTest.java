package com.gestionprojetagile.ProjetAgile.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.*;
import com.gestionprojetagile.ProjetAgile.web.exceptions.*;
import com.gestionprojetagile.ProjetAgile.web.repositories.*;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.SprintBacklogImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintBacklogImplTest {

    @Mock
    private SprintBacklogRepo sprintBacklogRepo;

    @Mock
    private UserStoryRepo userStoryRepo;

    @InjectMocks
    private SprintBacklogImpl sprintBacklogService;

    private SprintBacklog sprintBacklog;
    private UserStory userStory;
    private Task task;

    @BeforeEach
    void setUp() {
        sprintBacklog = new SprintBacklog();
        sprintBacklog.setId(1L);
        sprintBacklog.setUserstories(new ArrayList<>());

        userStory = new UserStory();
        userStory.setId(1L);
        userStory.setTasks(new ArrayList<>());

        task = new Task();
        task.setId(1L);
    }

    @Test
    void createSprintBacklog_ShouldReturnSavedSprintBacklog() {
        when(sprintBacklogRepo.save(any(SprintBacklog.class))).thenReturn(sprintBacklog);

        SprintBacklog result = sprintBacklogService.createSprintBacklog(sprintBacklog);

        assertNotNull(result);
        assertEquals(sprintBacklog.getId(), result.getId());
        verify(sprintBacklogRepo, times(1)).save(sprintBacklog);
    }

    @Test
    void updateSprintBacklog_WhenExists_ShouldReturnUpdatedSprintBacklog() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.of(sprintBacklog));
        when(sprintBacklogRepo.save(any(SprintBacklog.class))).thenReturn(sprintBacklog);

        SprintBacklog updated = new SprintBacklog();
        updated.setName("Updated Sprint");

        SprintBacklog result = sprintBacklogService.updateSprintBacklog(1L, updated);

        assertNotNull(result);
        verify(sprintBacklogRepo, times(1)).save(updated);
    }

    @Test
    void updateSprintBacklog_WhenNotExists_ShouldThrowException() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SprintBlNotFoundException.class, () -> {
            sprintBacklogService.updateSprintBacklog(1L, new SprintBacklog());
        });
    }

    @Test
    void getSprintBacklogById_WhenExists_ShouldReturnSprintBacklog() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.of(sprintBacklog));

        SprintBacklog result = sprintBacklogService.getSprintBacklogById(1L);

        assertNotNull(result);
        assertEquals(sprintBacklog.getId(), result.getId());
    }

    @Test
    void getSprintBacklogById_WhenNotExists_ShouldThrowException() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SprintBlNotFoundException.class, () -> {
            sprintBacklogService.getSprintBacklogById(1L);
        });
    }

    @Test
    void deleteSprintBacklog_WhenExists_ShouldDelete() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.of(sprintBacklog));
        doNothing().when(sprintBacklogRepo).delete(sprintBacklog);

        sprintBacklogService.deleteSprintBacklog(1L);

        verify(sprintBacklogRepo, times(1)).delete(sprintBacklog);
    }

    @Test
    void deleteSprintBacklog_WhenNotExists_ShouldThrowException() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SprintBlNotFoundException.class, () -> {
            sprintBacklogService.deleteSprintBacklog(1L);
        });
    }

    @Test
    void addUserStoriesToSprintBl_WhenExists_ShouldAddUserStory() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.of(sprintBacklog));

        sprintBacklogService.addUserStoriesToSprintBl(1L, userStory);

        assertFalse(sprintBacklog.getUserstories().isEmpty());
        assertEquals(1, sprintBacklog.getUserstories().size());
    }

    @Test
    void removeUserStoryFromSprintBacklog_WhenExists_ShouldRemoveUserStory() {
        sprintBacklog.getUserstories().add(userStory);
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.of(sprintBacklog));

        sprintBacklogService.removeUserStoryFromSprintBacklog(1L, 1L);

        assertTrue(sprintBacklog.getUserstories().isEmpty());
    }

    @Test
    void deleteTasksFromUserStory_WhenExists_ShouldRemoveTask() {
        userStory.getTasks().add(task);
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));

        sprintBacklogService.deleteTasksFromUserStory(1L, 1L);

        assertTrue(userStory.getTasks().isEmpty());
    }

    @Test
    void addTaskToUserStoryInSprintBacklog_WhenExists_ShouldAddTask() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));

        Task result = sprintBacklogService.addTaskToUserStoryInSprintBacklog(1L, task);

        assertNotNull(result);
        assertFalse(userStory.getTasks().isEmpty());
        assertEquals(1, userStory.getTasks().size());
    }
}