package com.gestionprojetagile.ProjetAgile.web.controller;



import com.gestionprojetagile.ProjetAgile.web.DTO.UtilisateurDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import com.gestionprojetagile.ProjetAgile.web.mapping.UtilisateurMapper;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/utilisateur")
public class UtilisateurController {
    private UtilisateurMapper mapper;
    private IUtilisateur userService;
    public  UtilisateurController(IUtilisateur utilisateur, UtilisateurMapper mapper)
    {
        this.mapper=mapper;
        this.userService=utilisateur;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UtilisateurDTO> createUser(@RequestBody UtilisateurDTO userDto) {
        Utilisateur utilisateur = mapper.utilisateurDtoToUtilisateur(userDto);
        Utilisateur createdUtilisateur = userService.createUser(utilisateur);
        UtilisateurDTO createdUserDto = mapper.utilisateurToUtilisateurDto(createdUtilisateur);
        return ResponseEntity.ok(createdUserDto);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UtilisateurDTO> getUser(@PathVariable Long id) {
        Utilisateur utilisateur = userService.getUserById(id);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        UtilisateurDTO userDTO = mapper.utilisateurToUtilisateurDto(utilisateur);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UtilisateurDTO>> getAllUser() {
        List<Utilisateur> utilisateurs = userService.getAllUsers();
        List<UtilisateurDTO> userDtos = utilisateurs.stream()
                .map(mapper::utilisateurToUtilisateurDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }
    @PutMapping("/modifierUser/{id}")
    public ResponseEntity<UtilisateurDTO> modifierUser(@PathVariable long id, @RequestBody UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur = mapper.utilisateurDtoToUtilisateur(utilisateurDTO);
        Utilisateur utilisateurModified = userService.updateUser(id,utilisateur);
        UtilisateurDTO utilisateurDTO1 = mapper.utilisateurToUtilisateurDto(utilisateurModified);
        return ResponseEntity.ok(utilisateurDTO1);
    }
    @DeleteMapping("/supprimerUser/{id}")
    public  ResponseEntity<Void> supprimerUser(@PathVariable long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }


}