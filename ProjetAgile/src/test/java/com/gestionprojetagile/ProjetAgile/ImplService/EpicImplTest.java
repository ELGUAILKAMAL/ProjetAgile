package com.gestionprojetagile.ProjetAgile.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.*;
import com.gestionprojetagile.ProjetAgile.web.exceptions.*;
import com.gestionprojetagile.ProjetAgile.web.repositories.*;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.EpicImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EpicImplTest {

    @Mock
    private EpicRepo epicRepo;

    @Mock
    private UserStoryRepo userStoryRepo;

    @InjectMocks
    private EpicImpl epicService;

    private Epic epic;
    private UserStory userStory;

    @BeforeEach
    void setUp() {
        epic = new Epic();
        epic.setId(1L);
        epic.setUserStories(new ArrayList<>());

        userStory = new UserStory();
        userStory.setId(1L);
    }

    @Test
    void createEpic_ShouldReturnSavedEpic() {
        when(epicRepo.save(any(Epic.class))).thenReturn(epic);

        Epic result = epicService.createEpic(epic);

        assertNotNull(result);
        assertEquals(epic.getId(), result.getId());
        verify(epicRepo, times(1)).save(epic);
    }

    @Test
    void getEpicById_WhenExists_ShouldReturnEpic() {
        when(epicRepo.findById(1L)).thenReturn(Optional.of(epic));

        Epic result = epicService.getEpicById(1L);

        assertNotNull(result);
        assertEquals(epic.getId(), result.getId());
    }

    @Test
    void getEpicById_WhenNotExists_ShouldThrowException() {
        when(epicRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EpicNotFoundException.class, () -> {
            epicService.getEpicById(1L);
        });
    }

    @Test
    void getAllEpics_ShouldReturnList() {
        when(epicRepo.findAll()).thenReturn(Arrays.asList(epic));

        List<Epic> result = epicService.getAllEpics();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void updateEpic_WhenExists_ShouldReturnUpdatedEpic() {
        when(epicRepo.findById(1L)).thenReturn(Optional.of(epic));
        when(epicRepo.save(any(Epic.class))).thenReturn(epic);

        Epic updated = new Epic();
        updated.setName("Updated Epic");
        updated.setDescription("Updated Description");

        Epic result = epicService.updateEpic(1L, updated);

        assertNotNull(result);
        verify(epicRepo, times(1)).save(epic);
    }

    @Test
    void deleteEpic_WhenExists_ShouldDelete() {
        when(epicRepo.findById(1L)).thenReturn(Optional.of(epic));
        doNothing().when(epicRepo).delete(epic);

        epicService.deleteEpic(1L);

        verify(epicRepo, times(1)).delete(epic);
    }

    @Test
    void deleteEpic_WhenNotExists_ShouldThrowException() {
        when(epicRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EpicNotFoundException.class, () -> {
            epicService.deleteEpic(1L);
        });
    }

    @Test
    void getUserStoriesByEpicId_WhenExists_ShouldReturnList() {
        epic.getUserStories().add(userStory);
        when(epicRepo.findById(1L)).thenReturn(Optional.of(epic));

        Collection<UserStory> result = epicService.getUserStoriesByEpicId(1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void decouperEpic_WhenExists_ShouldAddUserStories() {
        List<UserStory> newUserStories = Arrays.asList(userStory);
        when(epicRepo.findById(1L)).thenReturn(Optional.of(epic));

        List<UserStory> result = epicService.decouperEpic(1L, newUserStories);

        assertNotNull(result);
        assertFalse(epic.getUserStories().isEmpty());
        assertEquals(1, epic.getUserStories().size());
    }

    @Test
    void deleteUserStoryEpic_WhenBothExist_ShouldRemoveUserStory() {
        epic.getUserStories().add(userStory);
        when(epicRepo.findById(1L)).thenReturn(Optional.of(epic));
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));

        epicService.deleteUserStoryEpic(1L, 1L);

        assertTrue(epic.getUserStories().isEmpty());
    }

    @Test
    void deleteUserStoryEpic_WhenEitherNotExist_ShouldThrowException() {
        when(epicRepo.findById(1L)).thenReturn(Optional.empty());
        when(userStoryRepo.findById(1L)).thenReturn(Optional.of(userStory));

        assertThrows(EpicNotFoundException.class, () -> {
            epicService.deleteUserStoryEpic(1L, 1L);
        });
    }
}