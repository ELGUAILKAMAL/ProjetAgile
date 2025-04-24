package com.gestionprojetagile.ProjetAgile.web.service.ImplService;


import com.gestionprojetagile.ProjetAgile.web.Enities.Task;
import com.gestionprojetagile.ProjetAgile.web.Enities.UserStory;
import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import com.gestionprojetagile.ProjetAgile.web.exceptions.TaskNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.exceptions.UserNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.exceptions.UserStoryNotFoundException;
import com.gestionprojetagile.ProjetAgile.web.repositories.TaskRepo;
import com.gestionprojetagile.ProjetAgile.web.repositories.UserStoryRepo;
import com.gestionprojetagile.ProjetAgile.web.repositories.UtilisateurRepo;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.ITask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Ajout de l'annotation @Service pour que Spring gère cette classe comme un bean
public class TaskImpl implements ITask {

    private final TaskRepo taskRepo;
    private final UserStoryRepo userStoryRepo;
    private final UtilisateurRepo utilisateurRepo;

    // Injection des dépendances via le constructeur
    public TaskImpl(TaskRepo taskRepo, UserStoryRepo userStoryRepo, UtilisateurRepo utilisateurRepo) {
        this.taskRepo = taskRepo;
        this.userStoryRepo = userStoryRepo;
        this.utilisateurRepo = utilisateurRepo; // Correction de la faute de frappe
    }

    @Override
    public Task createTask(Long userStoryId, Task task) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(userStoryId);
        if (userStoryOptional.isPresent()) {
            task.setUserStory(userStoryOptional.get()); // Associer la tâche à la UserStory
            return taskRepo.save(task); // Sauvegarder la tâche et la retourner
        } else {
            throw new UserStoryNotFoundException("User Story avec l'ID " + userStoryId + " non trouvée");
        }
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tâche avec l'ID " + id + " non trouvée"));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        if (!taskRepo.existsById(id)) {
            throw new TaskNotFoundException("Tâche avec l'ID " + id + " non trouvée");
        }
        task.setId(id); // S'assurer que l'ID de la tâche est correct
        return taskRepo.save(task); // Mettre à jour et retourner la tâche
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepo.existsById(id)) {
            throw new TaskNotFoundException("Tâche avec l'ID " + id + " non trouvée");
        }
        taskRepo.deleteById(id); // Supprimer la tâche
    }

    @Override
    public List<Task> getTasksByUserStoryId(Long userStoryId) {
        Optional<UserStory> userStoryOptional = userStoryRepo.findById(userStoryId);
        if (userStoryOptional.isPresent()) {
            return taskRepo.findTaskByUserStory(userStoryOptional.get());
        } else {
            throw new UserStoryNotFoundException("User Story avec l'ID " + userStoryId + " non trouvée");
        }
    }

    @Override
    public Task updateTaskStatus(Long taskId, String status) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Tâche avec l'ID " + taskId + " non trouvée"));
        task.setStatus(status); // Mettre à jour le statut
        return taskRepo.save(task); // Sauvegarder et retourner la tâche mise à jour
    }

    @Override
    public List<Task> getTasksByAssignedUserId(Long userId) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepo.findById(userId);
        if (utilisateurOptional.isPresent()) {
            return taskRepo.findTaskByUtilisateur(utilisateurOptional.get());
        } else {
            throw new UserNotFoundException("Utilisateur avec l'ID " + userId + " non trouvé");
        }
    }
}