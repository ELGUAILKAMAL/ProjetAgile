package com.gestionprojetagile.ProjetAgile.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import com.gestionprojetagile.ProjetAgile.web.repositories.UtilisateurRepo;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceImplTest {

    @Mock
    private UtilisateurRepo utilisateurRepo; // Mock du repository

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService; // Classe à tester avec les mocks injectés

    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        // Initialisation d'un utilisateur pour les tests
        utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setPassword("password123");
        utilisateur.setRole("ADMIN");
    }

    // Test pour la méthode createUser
    @Test
    void testCreateUser() {
        // Simuler le comportement de utilisateurRepo.save
        when(utilisateurRepo.save(any(Utilisateur.class))).thenReturn(utilisateur);

        // Appeler la méthode à tester
        Utilisateur createdUser = utilisateurService.createUser(utilisateur);

        // Vérifier les résultats
        assertNotNull(createdUser);
        assertEquals(utilisateur.getId(), createdUser.getId());
        assertEquals(utilisateur.getNom(), createdUser.getNom());
        assertEquals(utilisateur.getPrenom(), createdUser.getPrenom());
        assertEquals(utilisateur.getPassword(), createdUser.getPassword());
        assertEquals(utilisateur.getRole(), createdUser.getRole());

        // Vérifier que utilisateurRepo.save a été appelé une fois
        verify(utilisateurRepo, times(1)).save(any(Utilisateur.class));
    }

    // Test pour la méthode getUserById
    @Test
    void testGetUserById() {
        // Simuler le comportement de utilisateurRepo.findById
        when(utilisateurRepo.findById(1L)).thenReturn(Optional.of(utilisateur));

        // Appeler la méthode à tester
        Utilisateur foundUser = utilisateurService.getUserById(1L);

        // Vérifier les résultats
        assertNotNull(foundUser);
        assertEquals(utilisateur.getId(), foundUser.getId());
        assertEquals(utilisateur.getNom(), foundUser.getNom());
        assertEquals(utilisateur.getPrenom(), foundUser.getPrenom());
        assertEquals(utilisateur.getPassword(), foundUser.getPassword());
        assertEquals(utilisateur.getRole(), foundUser.getRole());

        // Vérifier que utilisateurRepo.findById a été appelé une fois
        verify(utilisateurRepo, times(1)).findById(1L);
    }

    // Test pour la méthode getUserById avec un ID non trouvé
    @Test
    void testGetUserById_NotFound() {
        // Simuler le comportement de utilisateurRepo.findById pour un ID non trouvé
        when(utilisateurRepo.findById(2L)).thenReturn(Optional.empty());

        // Vérifier qu'une exception est levée
        Exception exception = assertThrows(RuntimeException.class, () -> {
            utilisateurService.getUserById(2L);
        });

        // Vérifier le message de l'exception
        assertEquals("Utilisateur non trouvé avec l'ID : 2", exception.getMessage());

        // Vérifier que utilisateurRepo.findById a été appelé une fois
        verify(utilisateurRepo, times(1)).findById(2L);
    }

    // Test pour la méthode getAllUsers
    @Test
    void testGetAllUsers() {
        // Simuler le comportement de utilisateurRepo.findAll
        when(utilisateurRepo.findAll()).thenReturn(Arrays.asList(utilisateur));

        // Appeler la méthode à tester
        List<Utilisateur> users = utilisateurService.getAllUsers();

        // Vérifier les résultats
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(utilisateur.getId(), users.get(0).getId());
        assertEquals(utilisateur.getNom(), users.get(0).getNom());
        assertEquals(utilisateur.getPrenom(), users.get(0).getPrenom());
        assertEquals(utilisateur.getPassword(), users.get(0).getPassword());
        assertEquals(utilisateur.getRole(), users.get(0).getRole());

        // Vérifier que utilisateurRepo.findAll a été appelé une fois
        verify(utilisateurRepo, times(1)).findAll();
    }

    // Test pour la méthode updateUser
    @Test
    void testUpdateUser() {
        // Simuler le comportement de utilisateurRepo.findById
        when(utilisateurRepo.findById(1L)).thenReturn(Optional.of(utilisateur));
        // Simuler le comportement de utilisateurRepo.save
        when(utilisateurRepo.save(any(Utilisateur.class))).thenReturn(utilisateur);

        // Créer un utilisateur avec des nouvelles valeurs
        Utilisateur updatedUser = new Utilisateur();
        updatedUser.setNom("Martin");
        updatedUser.setPrenom("Pierre");
        updatedUser.setPassword("newpassword123");
        updatedUser.setRole("USER");

        // Appeler la méthode à tester
        Utilisateur result = utilisateurService.updateUser(1L, updatedUser);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals(utilisateur.getId(), result.getId());
        assertEquals("Martin", result.getNom());
        assertEquals("Pierre", result.getPrenom());
        assertEquals("newpassword123", result.getPassword());
        assertEquals("USER", result.getRole());

        // Vérifier que utilisateurRepo.findById et utilisateurRepo.save ont été appelés
        verify(utilisateurRepo, times(1)).findById(1L);
        verify(utilisateurRepo, times(1)).save(any(Utilisateur.class));
    }

    // Test pour la méthode deleteUser
    @Test
    void testDeleteUser() {
        // Simuler le comportement de utilisateurRepo.deleteById
        doNothing().when(utilisateurRepo).deleteById(1L);

        // Appeler la méthode à tester
        utilisateurService.deleteUser(1L);

        // Vérifier que utilisateurRepo.deleteById a été appelé une fois
        verify(utilisateurRepo, times(1)).deleteById(1L);
    }

    // Test pour la méthode findByUsername
    @Test
    void testFindByUsername() {
        // Simuler le comportement de utilisateurRepo.findByNom
        when(utilisateurRepo.findByNom("Dupont")).thenReturn(Optional.of(utilisateur));

        // Appeler la méthode à tester
        Utilisateur foundUser = utilisateurService.findByUsername("Dupont");

        // Vérifier les résultats
        assertNotNull(foundUser);
        assertEquals(utilisateur.getId(), foundUser.getId());
        assertEquals(utilisateur.getNom(), foundUser.getNom());
        assertEquals(utilisateur.getPrenom(), foundUser.getPrenom());
        assertEquals(utilisateur.getPassword(), foundUser.getPassword());
        assertEquals(utilisateur.getRole(), foundUser.getRole());

        // Vérifier que utilisateurRepo.findByNom a été appelé une fois
        verify(utilisateurRepo, times(1)).findByNom("Dupont");
    }

    // Test pour la méthode findByUsername avec un nom d'utilisateur non trouvé
    @Test
    void testFindByUsername_NotFound() {
        // Simuler le comportement de utilisateurRepo.findByNom pour un nom non trouvé
        when(utilisateurRepo.findByNom("Inconnu")).thenReturn(Optional.empty());

        // Vérifier qu'une exception est levée
        Exception exception = assertThrows(RuntimeException.class, () -> {
            utilisateurService.findByUsername("Inconnu");
        });

        // Vérifier le message de l'exception
        assertEquals("Utilisateur non trouvé avec le nom d'utilisateur : Inconnu", exception.getMessage());

        // Vérifier que utilisateurRepo.findByNom a été appelé une fois
        verify(utilisateurRepo, times(1)).findByNom("Inconnu");
    }
}