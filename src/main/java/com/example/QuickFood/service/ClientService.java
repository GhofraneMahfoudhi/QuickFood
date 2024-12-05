package com.example.QuickFood.service;

import com.example.QuickFood.model.*;
import com.example.QuickFood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final RestaurantRepository restaurantRepository;
    private final CommandeRepository commandeRepository;
    private final AvisRepository avisRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(RestaurantRepository restaurantRepository,
                         CommandeRepository commandeRepository,
                         AvisRepository avisRepository ,
                         ClientRepository clientRepository) {
        this.restaurantRepository = restaurantRepository;
        this.commandeRepository = commandeRepository;
        this.avisRepository = avisRepository;
        this.clientRepository = clientRepository;
    }

    // Method to browse restaurants
    public List<Restaurant> parcourirRestos() {
        return restaurantRepository.findAll(); // Get all restaurants from the database
    }

    // Method to place an order
    public Commande passerCommande(Client client, Restaurant restaurant, List<Repas> repasList) {
        if (client == null || restaurant == null || repasList == null || repasList.isEmpty()) {
            throw new IllegalArgumentException("Client, restaurant, and repas list must be provided.");
        }

        Commande commande = new Commande();
        commande.setUtilisateur(client);
        commande.setRestaurant(restaurant);
        commande.setListeRepas(repasList.toString());
        commande.setEtat("Pending");

        return commandeRepository.save(commande); // Ensure this does not return null.
    }


    // Method to track an order
    public Commande suivreCommande(int idCommande) {
        return commandeRepository.findById(idCommande)
                .orElseThrow(() -> new IllegalArgumentException("Commande not found with id: " + idCommande)); // Retrieve order by ID
    }

    // Method to leave a review
    public Avis laisserAvis(int idClient, String contenu) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + idClient));
        Avis avis = new Avis();
        avis.setClient(client);
        avis.setCommentaire(contenu);
        return avisRepository.save(avis); // Save review
    }
}

