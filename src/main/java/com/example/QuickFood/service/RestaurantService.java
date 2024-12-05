package com.example.QuickFood.service;

import com.example.QuickFood.model.Restaurant;
import com.example.QuickFood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void manageRestaurant(String operation, Restaurant restaurant) {
        switch (operation) {
            case "create":
                restaurantRepository.save(restaurant); // Save the restaurant
                break;
            case "update":
                // Logic to update a restaurant
                restaurantRepository.save(restaurant); // Save changes
                break;
            case "delete":
                restaurantRepository.delete(restaurant); // Delete the restaurant
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
}
