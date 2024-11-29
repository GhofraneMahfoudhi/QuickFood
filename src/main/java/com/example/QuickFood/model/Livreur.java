package com.example.QuickFood.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor
@Entity

public class Livreur extends Utilisateur {

    @OneToOne
    @JoinColumn(name = "localisation_id", unique = true)
    private Localisation localisationActuelle;

    // Default Constructor
    public Livreur() {
    }

}
