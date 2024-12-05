package com.example.QuickFood.service;

import com.example.QuickFood.model.*;
import com.example.QuickFood.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private AvisRepository avisRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private Restaurant restaurant;
    private Commande commande;
    private Avis avis;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Setup mock objects with sample data
        client = new Client();
        client.setIdUtilisateur(1);
        client.setNomU("client1");

        restaurant = new Restaurant();
        restaurant.setIdResto(1);
        restaurant.setNomResto("Restaurant1");

        commande = new Commande();
        commande.setIdCommande(1);
        commande.setEtat("Pending");
        commande.setUtilisateur(client);
        commande.setRestaurant(restaurant);

        avis = new Avis();
        avis.setIdAvis(1);
        avis.setCommentaire("Great service!");
    }

    @Test
    void testParcourirRestos() {
        // Arrange
        List<Restaurant> restos = new ArrayList<>();
        restos.add(restaurant);
        when(restaurantRepository.findAll()).thenReturn(restos);

        // Act
        List<Restaurant> result = clientService.parcourirRestos();

        // Assert
        assertEquals(1, result.size());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    void testPasserCommande() {
        // Arrange
        System.out.println("Client: " + client);
        System.out.println("Restaurant: " + restaurant);

        List<Repas> repasList = new ArrayList<>();
        Repas repas = new Repas();
        repas.setNomRepas("Pizza");
        repasList.add(repas);

        when(commandeRepository.save(any(Commande.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Commande result = clientService.passerCommande(client, restaurant, repasList);

        // Debugging
        System.out.println("Result: " + result);

        // Assert
        assertNotNull(result, "Commande should not be null");
        assertEquals("Pending", result.getEtat());
        assertEquals(client, result.getUtilisateur());
        assertEquals(restaurant, result.getRestaurant());

        // Verify save is called
        verify(commandeRepository, times(1)).save(any(Commande.class));
    }





    @Test
    void testSuivreCommande() {
        // Arrange
        when(commandeRepository.findById(1)).thenReturn(Optional.of(commande));

        // Act
        Commande result = clientService.suivreCommande(1);

        // Assert
        assertNotNull(result);
        verify(commandeRepository, times(1)).findById(1);
    }

    @Test
    void testSuivreCommandeNotFound() {
        // Arrange
        when(commandeRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.suivreCommande(999);
        });

        assertEquals("Commande not found with id: 999", exception.getMessage());
    }

    @Test
    void testLaisserAvis() {
        // Arrange
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(avisRepository.save(any(Avis.class))).thenReturn(avis);

        // Act
        Avis result = clientService.laisserAvis(1, "Great service!");

        // Assert
        assertNotNull(result);
        assertEquals("Great service!", result.getCommentaire());
        verify(avisRepository, times(1)).save(any(Avis.class));
    }

    @Test
    void testLaisserAvisClientNotFound() {
        // Arrange
        when(clientRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.laisserAvis(999, "Great service!");
        });

        assertEquals("Client not found with id: 999", exception.getMessage());
    }
}
