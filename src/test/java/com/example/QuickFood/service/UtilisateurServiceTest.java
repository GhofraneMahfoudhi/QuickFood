package com.example.QuickFood.service;

import com.example.QuickFood.model.Utilisateur;
import com.example.QuickFood.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1);
        utilisateur.setNomU("John");
        utilisateur.setPrenom("Doe");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");
    }

    @Test
    public void testCreerUtilisateur() {
        // Arrange: Mock repository's save method
        when(utilisateurRepository.save(Mockito.any(Utilisateur.class))).thenReturn(utilisateur);

        // Act: Call the service method
        Utilisateur result = utilisateurService.creerUtilisateur(utilisateur);

        // Assert: Verify the result
        assertNotNull(result);
        assertEquals("John", result.getNomU());
        assertEquals("Doe", result.getPrenom());
        verify(utilisateurRepository, times(1)).save(Mockito.any(Utilisateur.class));
    }

    @Test
    public void testModifierUtilisateur() {
        // Arrange: Mock repository's findById and save methods
        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(utilisateur));
        when(utilisateurRepository.save(Mockito.any(Utilisateur.class))).thenReturn(utilisateur);

        // Act: Call the service method
        Utilisateur result = utilisateurService.modifierUtilisateur(1, utilisateur);

        // Assert: Verify the result
        assertNotNull(result);
        assertEquals(1, result.getIdUtilisateur());
        verify(utilisateurRepository, times(1)).findById(1);
        verify(utilisateurRepository, times(1)).save(Mockito.any(Utilisateur.class));
    }

    @Test
    public void testSupprimerUtilisateur() {
        // Act: Call the service method
        utilisateurService.supprimerUtilisateur(1);

        // Assert: Verify that the deleteById method was called
        verify(utilisateurRepository, times(1)).deleteById(1);
    }

    @Test
    public void testTrouverParId() {
        // Arrange: Mock repository's findById method
        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(utilisateur));

        // Act: Call the service method
        Utilisateur result = utilisateurService.trouverParId(1);

        // Assert: Verify the result
        assertNotNull(result);
        assertEquals(1, result.getIdUtilisateur());
    }

    @Test
    public void testSeConnecter() {
        // Arrange: Mock repository's findByEmailAndMotDePasse method
        when(utilisateurRepository.findByEmailAndMotDePasse("john.doe@example.com", "password"))
                .thenReturn(Optional.of(utilisateur));

        // Act: Call the service method
        Utilisateur result = utilisateurService.seConnecter("john.doe@example.com", "password");

        // Assert: Verify the result
        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    public void testListerUtilisateurs() {
        // Arrange: Mock repository's findAll method
        when(utilisateurRepository.findAll()).thenReturn(List.of(utilisateur));

        // Act: Call the service method
        List<Utilisateur> utilisateurs = utilisateurService.listerUtilisateurs();

        // Assert: Verify the result
        assertNotNull(utilisateurs);
        assertFalse(utilisateurs.isEmpty());
        assertEquals(1, utilisateurs.size());
    }
}

