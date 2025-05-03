package com.gestionprojetagile.ProjetAgile.web.repositories;


import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByNom(String nom);
    Utilisateur findUtilisateursByEmail(String Email);
}
