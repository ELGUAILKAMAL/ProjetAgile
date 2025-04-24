package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.EpicDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IEpic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/epics")
public class EpicController {

    private IEpic epicService;
    public EpicController(IEpic epicService){
        this.epicService=epicService;
    }
    @PostMapping
    public ResponseEntity<EpicDTO> createEpic(@RequestBody EpicDTO epicDTO) {
        Epic epic = convertToEntity(epicDTO);
        Epic createdEpic = epicService.createEpic(epic);
        return ResponseEntity.ok(convertToDTO(createdEpic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpicDTO> getEpicById(@PathVariable Long id) {
        Epic epic = epicService.getEpicById(id);
        return ResponseEntity.ok(convertToDTO(epic));
    }

    @GetMapping
    public ResponseEntity<List<EpicDTO>> getAllEpics() {
        List<Epic> epics = epicService.getAllEpics();
        List<EpicDTO> epicDTOs = epics.stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(epicDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpicDTO> updateEpic(@PathVariable Long id, @RequestBody EpicDTO epicDTO) {
        Epic epic = convertToEntity(epicDTO);
        Epic updatedEpic = epicService.updateEpic(id, epic);
        return ResponseEntity.ok(convertToDTO(updatedEpic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Long id) {
        epicService.deleteEpic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/user-stories")
    public ResponseEntity<List<UserStory>> getUserStoriesByEpicId(@PathVariable Long id) {
        Collection<UserStory> userStories = epicService.getUserStoriesByEpicId(id);
        return ResponseEntity.ok(List.copyOf(userStories));
    }

    @PostMapping("/{id}/decompose")
    public ResponseEntity<List<UserStory>> decomposeEpic(
            @PathVariable Long id,
            @RequestBody List<UserStory> userStories) {
        List<UserStory> decomposedStories = epicService.decouperEpic(id, userStories);
        return ResponseEntity.ok(decomposedStories);
    }

    @DeleteMapping("/{epicId}/user-stories/{userStoryId}")
    public ResponseEntity<Void> removeUserStoryFromEpic(
            @PathVariable Long epicId,
            @PathVariable Long userStoryId) {
        epicService.deleteUserStoryEpic(epicId, userStoryId);
        return ResponseEntity.noContent().build();
    }

    private Epic convertToEntity(EpicDTO dto) {
        Epic epic = new Epic();
        epic.setId(dto.getId());
        epic.setName(dto.getName());
        epic.setDescription(dto.getDescription());
        return epic;
    }

    private EpicDTO convertToDTO(Epic epic) {
        return new EpicDTO(
                epic.getId(),
                epic.getName(),
                epic.getDescription()
        );
    }
}