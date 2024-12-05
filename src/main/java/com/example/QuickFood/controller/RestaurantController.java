package com.example.QuickFood.controller;

import com.example.QuickFood.model.Restaurant;
import com.example.QuickFood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/manage")
    public ResponseEntity<?> manageRestaurant(
            @RequestParam String operation,
            @RequestBody Restaurant restaurant) {
        try {
            Restaurant Restaurant = null;
            restaurantService.manageRestaurant(operation, Restaurant);
            return ResponseEntity.ok("Operation successful: " + operation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
