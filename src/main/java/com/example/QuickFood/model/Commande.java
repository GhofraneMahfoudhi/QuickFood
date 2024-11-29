package com.example.QuickFood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import java.util.List ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor

@Table(name = "commande")
public class Commande {

    @Id
    private int idCommande;

    @Column(name = "liste_repas", nullable = false)
    private String listeRepas;

    @Column(name = "total", nullable = false)
    private float total;

    @Column(name = "etat", nullable = false)
    private String etat;

    @Column(name = "localisation_livreur", nullable = false)
    private int localisationLivreur;

    @Column(name = "quantite", nullable = false)
    private int quantite;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "temps", nullable = false)
    private Time temps;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name = "commande_repas",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "repas_id"))
    private List<Repas> repas;

    @OneToMany(mappedBy = "commande")
    private List<Avis> avis;

    // Default Constructor
    public Commande() {
    }

}