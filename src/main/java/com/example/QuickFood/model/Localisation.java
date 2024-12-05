package com.example.QuickFood.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor
@Table(name = "localisation")
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocalisation;

    @Column(name = "latitude", nullable = false)
    private int latitude;

    @Column(name = "longitude", nullable = false)
    private int longitude;

    @OneToOne(mappedBy = "localisation")
    private Restaurant restaurant;

    @OneToOne(mappedBy = "localisationActuelle")
    private Livreur livreur;



    // Default Constructor
    public Localisation() {
    }
}
