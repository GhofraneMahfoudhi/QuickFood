package com.example.QuickFood.repository;


import com.example.QuickFood.model.Avis;
import com.example.QuickFood.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    // Find restaurants by name
    List<Restaurant> findByNomResto(String nomResto);

    // Find restaurants by Location
    List<Restaurant> findByLocalisation_LatitudeAndLocalisation_Longitude(double latitude, double longitude);

    // Find restaurants by category
    List<Restaurant> findByCategorie(String categorie);

    // Method to fetch details of a restaurant (for example)
    // This can be used to fetch detailed information about a specific restaurant
    // You can customize this method based on what "details" mean in your context
    Restaurant findByIdResto(int idResto);

    // Method to fetch all reviews (Avis) for a specific restaurant
    // Let's assume there is an Avis entity that holds reviews for restaurants
    List<Avis> findByAvis_Restaurant_IdResto(int idResto);


}
