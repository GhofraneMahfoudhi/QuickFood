package com.example.QuickFood.service;

import com.example.QuickFood.model.*;
import com.example.QuickFood.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AdministrateurServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RepasRepository repasRepository;

    @Mock
    private LivreurRepository livreurRepository;

    @Mock
    private AvisRepository avisRepository;

    @InjectMocks
    private AdministrateurService administrateurService;

    private Commande commande;
    private Restaurant restaurant;
    private Repas repas;
    private Livreur livreur;
    private Avis avis;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the entities
        commande = new Commande();
        commande.setIdCommande(1);
        commande.setEtat("Pending");

        restaurant = new Restaurant();
        restaurant.setIdResto(1);

        repas = new Repas();
        repas.setIdRepas(1);

        livreur = new Livreur();
        livreur.setIdUtilisateur(1);

        avis = new Avis();
        avis.setIdAvis(1);
    }

    @Test
    public void testGererCommandes_Approve() {
        when(commandeRepository.findById(1)).thenReturn(Optional.of(commande));

        administrateurService.gererCommandes("approve", 1, 0);

        assertEquals("Approved", commande.getEtat());
        verify(commandeRepository, times(1)).save(commande);
    }

    @Test
    public void testGererCommandes_Cancel() {
        when(commandeRepository.findById(1)).thenReturn(Optional.of(commande));

        administrateurService.gererCommandes("cancel", 1, 0);

        assertEquals("Canceled", commande.getEtat());
        verify(commandeRepository, times(1)).save(commande);
    }

    @Test
    public void testGererCommandes_Assign() {
        when(commandeRepository.findById(1)).thenReturn(Optional.of(commande));
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

        administrateurService.gererCommandes("assign", 1, 1);

        assertEquals(restaurant, commande.getRestaurant());
        verify(commandeRepository, times(1)).save(commande);
    }

    @Test
    public void testGererCommandes_InvalidAction() {
        assertThrows(IllegalArgumentException.class, () -> {
            administrateurService.gererCommandes("invalid", 1, 0);
        });
    }

    @Test
    public void testGererRestos_Create() {
        administrateurService.gererRestos("create", restaurant);

        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    public void testGererRepas_Create() {
        administrateurService.gererRepas("create", repas);

        verify(repasRepository, times(1)).save(repas);
    }

    @Test
    public void testGererAvis_Delete() {
        when(avisRepository.findById(1)).thenReturn(Optional.of(avis));

        administrateurService.gererAvis("delete", 1);

        verify(avisRepository, times(1)).delete(avis);
    }
}
