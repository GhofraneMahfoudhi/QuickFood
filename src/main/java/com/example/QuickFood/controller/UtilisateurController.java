package com.example.QuickFood.controller;

import com.example.QuickFood.model.Utilisateur;
import com.example.QuickFood.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<Utilisateur> createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        // Log the received object to ensure it's being correctly parsed
        System.out.println("Received request to create user: " + utilisateur);

        // Create the user
        Utilisateur createdUtilisateur = utilisateurService.creerUtilisateur(utilisateur);

        // Return response
        return ResponseEntity.ok(createdUtilisateur);
    }

    // Update an existing user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(
            @PathVariable("id") int idUtilisateur,
            @Valid @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.modifierUtilisateur(idUtilisateur, utilisateur);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("id") int idUtilisateur) {
        utilisateurService.supprimerUtilisateur(idUtilisateur);
        return ResponseEntity.noContent().build();
    }

    // Get a user by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") int idUtilisateur) {
        Utilisateur utilisateur = utilisateurService.trouverParId(idUtilisateur);
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.listerUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Utilisateur> login(@RequestParam String email, @RequestParam String motDePasse) {
        Utilisateur utilisateur = utilisateurService.seConnecter(email, motDePasse);
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }
}
