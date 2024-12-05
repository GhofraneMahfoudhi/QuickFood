package com.example.QuickFood.service;

import com.example.QuickFood.model.Utilisateur;
import com.example.QuickFood.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur modifierUtilisateur(int idUtilisateur, Utilisateur utilisateur) {
        Utilisateur existingUser = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException("User not found"));
        utilisateur.setIdUtilisateur(idUtilisateur);
        return utilisateurRepository.save(utilisateur);
    }

    public void supprimerUtilisateur(int idUtilisateur) {
        utilisateurRepository.deleteById(idUtilisateur);
    }

    public Utilisateur trouverParId(int idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur).orElse(null);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur seConnecter(String email, String motDePasse) {
        return utilisateurRepository.findByEmailAndMotDePasse(email, motDePasse).orElse(null);
    }
}
