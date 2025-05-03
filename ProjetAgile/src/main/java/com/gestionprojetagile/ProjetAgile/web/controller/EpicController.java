package com.gestionprojetagile.ProjetAgile.web.controller;


import com.gestionprojetagile.ProjetAgile.web.DTO.EpicDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.mapping.EpicMapper;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IEpic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/epics")
public class EpicController {
    private EpicMapper epicMapper;
    private IEpic epicService;
    public EpicController(IEpic epicService, EpicMapper epicMapper){
        this.epicMapper=epicMapper;
        this.epicService=epicService;
    }
    @PostMapping("/createEpic")
    public ResponseEntity<EpicDTO> createEpic(@RequestBody EpicDTO epicDTO) {
        Epic epic = epicMapper.epicDtoToEpic(epicDTO);
        Epic createdEpic = epicService.createEpic(epic);
        return ResponseEntity.ok(epicMapper.epicToEpicDto(createdEpic));
    }

    @GetMapping("/getEpic/{id}")
    public ResponseEntity<EpicDTO> getEpicById(@PathVariable Long id) {
        Epic epic = epicService.getEpicById(id);
        return ResponseEntity.ok(epicMapper.epicToEpicDto(epic));
    }

    @GetMapping("/getEpics")
    public ResponseEntity<List<EpicDTO>> getAllEpics() {
        List<Epic> epics = epicService.getAllEpics();
        List<EpicDTO> epicDTOs = new ArrayList<>();
        for(Epic epic : epics){
            epicDTOs.add(epicMapper.epicToEpicDto(epic));
        }
        return ResponseEntity.ok(epicDTOs);
    }

    @PutMapping("/modifyEpic/{id}")
    public ResponseEntity<EpicDTO> updateEpic(@PathVariable Long id, @RequestBody EpicDTO epicDTO) {
        Epic epic = epicMapper.epicDtoToEpic(epicDTO);
        Epic updatedEpic = epicService.updateEpic(id, epic);
        return ResponseEntity.ok(epicMapper.epicToEpicDto(updatedEpic));
    }

    @DeleteMapping("/deleteEpic/{id}")
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



}