package com.example.QuickFood.model;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor
@Entity
@Table(name = "livreur")
@PrimaryKeyJoinColumn(name = "id_utilisateur")
public class Livreur extends Utilisateur {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "localisation_id", unique = true, nullable = false)
    private Localisation localisationActuelle;

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Paiement> paiements;

    // Default Constructor
    public Livreur() {
        super();
    }

}
