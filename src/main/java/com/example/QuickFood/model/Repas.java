package com.example.QuickFood.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor

@Table(name = "repas")
public class Repas {

    @Id
    private int idRepas;

    @Column(name = "nom_repas", nullable = false)
    private String nomRepas;

    @Column(name = "prix", nullable = false)
    private float prix;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "idResto", nullable = false)
    private int idResto;

    @ManyToMany(mappedBy = "repas")
    private List<Commande> commandes;

    @OneToMany(mappedBy = "repas")
    private List<Avis> avis;

    // Default Constructor
    public Repas() {
    }
}