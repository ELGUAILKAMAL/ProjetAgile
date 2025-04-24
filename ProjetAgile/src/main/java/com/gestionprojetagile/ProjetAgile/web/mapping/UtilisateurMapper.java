package com.gestionprojetagile.ProjetAgile.web.mapping;



import com.gestionprojetagile.ProjetAgile.web.DTO.UtilisateurDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDTO utilisateurToUtilisateurDto(Utilisateur utilisateur);
    Utilisateur utilisateurDtoToUtilisateur(UtilisateurDTO utilisateurDTO);
}
