package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.UtilisateurDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T14:39:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UtilisateurMapperImpl implements UtilisateurMapper {

    @Override
    public UtilisateurDTO utilisateurToUtilisateurDto(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

        utilisateurDTO.setId( utilisateur.getId() );
        utilisateurDTO.setNom( utilisateur.getNom() );
        utilisateurDTO.setPrenom( utilisateur.getPrenom() );
        utilisateurDTO.setEmail( utilisateur.getEmail() );
        utilisateurDTO.setPassword( utilisateur.getPassword() );
        utilisateurDTO.setRole( utilisateur.getRole() );

        return utilisateurDTO;
    }

    @Override
    public Utilisateur utilisateurDtoToUtilisateur(UtilisateurDTO utilisateurDTO) {
        if ( utilisateurDTO == null ) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId( utilisateurDTO.getId() );
        utilisateur.setNom( utilisateurDTO.getNom() );
        utilisateur.setPrenom( utilisateurDTO.getPrenom() );
        utilisateur.setEmail( utilisateurDTO.getEmail() );
        utilisateur.setPassword( utilisateurDTO.getPassword() );
        utilisateur.setRole( utilisateurDTO.getRole() );

        return utilisateur;
    }
}
