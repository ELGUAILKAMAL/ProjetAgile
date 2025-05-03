package com.gestionprojetagile.ProjetAgile.web.service.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import com.gestionprojetagile.ProjetAgile.web.repositories.UtilisateurRepo;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements IUtilisateur {

        @Autowired
        private UtilisateurRepo utilisateurRepo;

        @Override
        public Utilisateur createUser(Utilisateur utilisateur) {
            return utilisateurRepo.save(utilisateur);
        }

        @Override
        public Utilisateur getUserById(Long id) {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepo.findById(id);
            return utilisateurOptional.orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + id));
        }

        @Override
        public List<Utilisateur> getAllUsers() {
            return utilisateurRepo.findAll();
        }

        @Override
        public Utilisateur updateUser(Long id, Utilisateur utilisateur) {
            Utilisateur existingUser = getUserById(id);

            existingUser.setNom(utilisateur.getNom());
            existingUser.setPrenom(utilisateur.getPrenom());
            existingUser.setNom(utilisateur.getNom());
            existingUser.setPassword(utilisateur.getPassword());
            existingUser.setRole(utilisateur.getRole());

            return utilisateurRepo.save(existingUser);
        }
        @Override
        public Utilisateur findByEmail(String email){
            return utilisateurRepo.findUtilisateursByEmail(email);
        }
        @Override
        public void deleteUser(Long id) {
            utilisateurRepo.deleteById(id);
        }

        @Override
        public Utilisateur findByUsername(String username) {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepo.findByNom((username));
            return utilisateurOptional.orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));
        }
}
