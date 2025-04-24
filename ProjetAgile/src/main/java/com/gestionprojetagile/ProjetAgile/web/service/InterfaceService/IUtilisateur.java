package com.gestionprojetagile.ProjetAgile.web.service.InterfaceService;


import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;

import java.util.List;

public interface IUtilisateur {
        Utilisateur createUser(Utilisateur utilisateur);
        Utilisateur getUserById(Long id);
        List<Utilisateur> getAllUsers();
        Utilisateur updateUser(Long id, Utilisateur utilisateur);
        void deleteUser(Long id);
        Utilisateur findByUsername(String username);
}
