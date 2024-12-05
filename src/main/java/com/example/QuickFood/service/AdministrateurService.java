package com.example.QuickFood.service;

import com.example.QuickFood.model.*;
import com.example.QuickFood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurService {

    private final CommandeRepository commandeRepository;
    private final LivreurRepository livreurRepository;
    private final RestaurantRepository restaurantRepository;
    private final RepasRepository repasRepository;
    private final AvisRepository avisRepository;

    @Autowired
    public AdministrateurService(AdministrateurRepository administrateurRepository,
                                 CommandeRepository commandeRepository,
                                 LivreurRepository livreurRepository,
                                 RestaurantRepository restaurantRepository ,
                                 RepasRepository repasRepository , AvisRepository avisRepository) {
        this.commandeRepository = commandeRepository;
        this.livreurRepository = livreurRepository;
        this.restaurantRepository = restaurantRepository;
        this.repasRepository = repasRepository;
        this.avisRepository = avisRepository;
    }

    public void gererRestos(String action, Restaurant restaurant) {
        switch (action.toLowerCase()) {
            case "create":
                restaurantRepository.save(restaurant);
                break;
            case "update":
                if (restaurant.getIdResto() != 0 && restaurantRepository.existsById(restaurant.getIdResto())) {
                    restaurantRepository.save(restaurant);
                } else {
                    throw new IllegalArgumentException("Restaurant not found or ID is null.");
                }
                break;
            case "view":
                List<Restaurant> restaurants = restaurantRepository.findAll();
                restaurants.forEach(System.out::println);
                break;
            case "delete":
                if (restaurant.getIdResto() != 0 && restaurantRepository.existsById(restaurant.getIdResto())) {
                    restaurantRepository.deleteById(restaurant.getIdResto());
                } else {
                    throw new IllegalArgumentException("Restaurant not found or ID is null.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid action. Use 'create', 'update', 'view', or 'delete'.");
        }
    }
    public void gererRepas(String action, Repas repas) {
        switch (action.toLowerCase()) {
            case "create":
                repasRepository.save(repas);
                break;
            case "update":
                if (repas.getIdRepas() != 0 && repasRepository.existsById(repas.getIdRepas())) {
                    repasRepository.save(repas);
                } else {
                    throw new IllegalArgumentException("Repas not found or ID is null.");
                }
                break;
            case "delete":
                if (repas.getIdRepas() != 0 && repasRepository.existsById(repas.getIdRepas())) {
                    repasRepository.deleteById(repas.getIdRepas());
                } else {
                    throw new IllegalArgumentException("Repas not found or ID is null.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid action. Use 'create', 'update', or 'delete'.");
        }
    }
    public void gererCommandes(String action , int idCommande, int restaurantId) {
        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new IllegalArgumentException("Commande not found with id: " + idCommande));

        switch (action.toLowerCase()) {
            case "approve":
                commande.setEtat("Approved");
                commandeRepository.save(commande);
                break;
            case "cancel":
                commande.setEtat("Canceled");
                commandeRepository.save(commande);
                break;
            case "assign":
                if (restaurantId != 0) {
                    Restaurant restaurant = restaurantRepository.findById(restaurantId)
                            .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + restaurantId));
                    commande.setRestaurant(restaurant);
                    commandeRepository.save(commande);
                } else {
                    throw new IllegalArgumentException("Restaurant ID is required for assigning a restaurant.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid action. Use 'approve', 'cancel', or 'assign'.");
        }
    }

    public void gererAvis(String action, int idAvis) {
        switch (action.toLowerCase()) {
            case "delete":
                Avis avis = avisRepository.findById(idAvis)
                        .orElseThrow(() -> new IllegalArgumentException("Avis not found with id: " + idAvis));
                avisRepository.delete(avis);
                break;
            default:
                throw new IllegalArgumentException("Invalid action. Use 'delete'.");
        }
    }


    public void attribuerLivreur(int idCommande, int idLivreur) {
        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new IllegalArgumentException("Commande not found with id: " + idCommande));
        Livreur livreur = livreurRepository.findById(idLivreur)
                .orElseThrow(() -> new IllegalArgumentException("Livreur not found with id: " + idLivreur));
        commande.setLivreur(livreur);
        commandeRepository.save(commande);
    }
}
