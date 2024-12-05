package com.example.QuickFood.repository;

import com.example.QuickFood.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    // Find all orders (Commandes) for a specific restaurant
    List<Commande> findByRestaurant_IdResto(int idResto);

    // Find all orders (Commandes) for a specific user (client)
    List<Commande> findByUtilisateur_IdUtilisateur(int idUtilisateur);

    // Find orders by status (etat)
    List<Commande> findByEtat(String etat);

    // Find orders by date (for example: today, this week, etc.)
    List<Commande> findByDate(java.sql.Date date);

    // Find orders by the total amount (e.g., orders over a certain amount)
    List<Commande> findByTotalGreaterThan(float total);

    // Find orders by quantity
    List<Commande> findByQuantite(int quantite);

}

