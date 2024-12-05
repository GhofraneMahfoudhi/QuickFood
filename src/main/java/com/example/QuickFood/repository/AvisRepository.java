package com.example.QuickFood.repository;

import com.example.QuickFood.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Integer> {

    // Find all reviews for a specific restaurant
    List<Avis> findByRestaurant_IdResto(int restaurantId);

    // Find all reviews from a specific client
    List<Avis> findByClient_IdUtilisateur(int clientId);

    // Find reviews by the repas (meal) id
    List<Avis> findByRepas_IdRepas(int repasId);

    // Find all reviews by a specific note (rating)
    List<Avis> findByNote(int note);

}
