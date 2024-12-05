package com.example.QuickFood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.QuickFood.model.Utilisateur;


import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
    // Add the custom query method
    Optional<Utilisateur> findByEmailAndMotDePasse(String email, String motDePasse);
}

