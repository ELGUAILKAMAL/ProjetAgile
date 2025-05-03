package com.gestionprojetagile.ProjetAgile.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.*;
import com.gestionprojetagile.ProjetAgile.web.exceptions.*;
import com.gestionprojetagile.ProjetAgile.web.repositories.*;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.UserStoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserStoryImplTest {

    @Mock
    private UserStoryRepo userStoryRepo;

    @Mock
    private SprintBacklogRepo sprintBacklogRepo;

    @Mock
    private ProdBacklogRepo prodBacklogRepo;

    @Mock
    private EpicRepo epicRepo;

    @InjectMocks
    private UserStoryImpl userStoryService;

    private UserStory userStory;
    private ProductBacklog productBacklog;
    private SprintBacklog sprintBacklog;
    private Epic epic;

    @BeforeEach
    void setUp() {
        userStory = new UserStory();
        userStory.setId(1L);
        userStory.setTitle("Test User Story");

        productBacklog = new ProductBacklog();
        productBacklog.setId(1L);
        productBacklog.setUserStories(Collections.singletonList(userStory));

        sprintBacklog = new SprintBacklog();
        sprintBacklog.setId(1L);

        epic = new Epic();
        epic.setId(1L);
    }

    @Test
    void createUserStory_ShouldReturnSavedUserStory() {
        when(userStoryRepo.save(any(UserStory.class))).thenReturn(userStory);

        UserStory result = userStoryService.createUserStory(userStory);

        assertNotNull(result);
        assertEquals(userStory.getId(), result.getId());
        verify(userStoryRepo, times(1)).save(userStory);
    }

    @Test
    void getUserStoryById_WhenExists_ShouldReturnUserStory() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));

        UserStory result = userStoryService.getUserStoryById(1L);

        assertNotNull(result);
        assertEquals(userStory.getId(), result.getId());
    }

    @Test
    void getUserStoryById_WhenNotExists_ShouldReturnNull() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.empty());

        UserStory result = userStoryService.getUserStoryById(1L);

        assertNull(result);
    }

    @Test
    void updateUserStory_WhenExists_ShouldReturnUpdatedUserStory() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));
        when(userStoryRepo.save(any(UserStory.class))).thenReturn(userStory);

        UserStory updated = new UserStory();
        updated.setTitle("Updated Description");

        UserStory result = userStoryService.updateUserStory(1L, updated);

        assertNotNull(result);
        verify(userStoryRepo, times(1)).save(updated);
    }

    @Test
    void updateUserStory_WhenNotExists_ShouldThrowException() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserStoryNotFoundException.class, () -> {
            userStoryService.updateUserStory(1L, new UserStory());
        });
    }

    @Test
    void getAllUserStories_ShouldReturnList() {
        when(userStoryRepo.findAll()).thenReturn(Arrays.asList(userStory));

        List<UserStory> result = userStoryService.getAllUserStories();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void deleteUserStory_WhenExists_ShouldDelete() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));
        doNothing().when(userStoryRepo).delete(userStory);

        userStoryService.deleteUserStory(1L);

        verify(userStoryRepo, times(1)).delete(userStory);
    }

    @Test
    void deleteUserStory_WhenNotExists_ShouldThrowException() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserStoryNotFoundException.class, () -> {
            userStoryService.deleteUserStory(1L);
        });
    }

    @Test
    void updateUserStoryStatus_WhenExists_ShouldUpdateStatus() {
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));
        when(userStoryRepo.save(any(UserStory.class))).thenReturn(userStory);

        UserStory result = userStoryService.updateUserStoryStatus(1L, "Done");

        assertNotNull(result);
        assertEquals("Done", result.getStatus());
    }

    @Test
    void getUserStoriesByProductBacklogId_WhenExists_ShouldReturnList() {
        when(prodBacklogRepo.findById(1L)).thenReturn(Optional.of(productBacklog));

        Collection<UserStory> result = userStoryService.getUserStoriesByProductBacklogId(1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }


    @Test
    void prioritizeUserStoriesProdBl_WhenExists_ShouldPrioritize() {
        when(prodBacklogRepo.findById(1L)).thenReturn(Optional.of(productBacklog));
        when(userStoryRepo.findAllByProductBacklogOrderByPriority(productBacklog))
                .thenReturn(Collections.singletonList(userStory));

        userStoryService.prioritizeUserStoriesProdBl(1L);

        verify(prodBacklogRepo, times(1)).findById(1L);
        assertFalse(productBacklog.getUserStories().isEmpty());
    }

    @Test
    void prioritizeUserStoriesSprintBl_WhenExists_ShouldPrioritize() {
        when(sprintBacklogRepo.findById(1L)).thenReturn(Optional.of(sprintBacklog));
        when(userStoryRepo.findAllBySprintBacklogOrderByPriority(sprintBacklog))
                .thenReturn(Collections.singletonList(userStory));

        userStoryService.prioritizeUserStoriesSprintBl(1L);

        verify(sprintBacklogRepo, times(1)).findById(1L);
        assertFalse(sprintBacklog.getUserstories().isEmpty());
    }
}