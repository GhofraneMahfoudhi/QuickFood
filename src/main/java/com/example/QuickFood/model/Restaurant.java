package com.example.QuickFood.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor

@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResto;

    @Column(name = "nom_resto", nullable = false)
    private String nomResto;

    @Column(name = "categorie", nullable = false)
    private String categorie;

    @Column(name = "image")
    private String image;

    @OneToOne
    @JoinColumn(name = "localisation_id" , unique = true)
    private Localisation localisation;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus;

    @OneToMany(mappedBy = "restaurant")
    private List<Commande> commandes;

    @OneToMany(mappedBy = "restaurant")
    private List<Avis> avis;

    // Default Constructor
    public Restaurant() {
    }
}