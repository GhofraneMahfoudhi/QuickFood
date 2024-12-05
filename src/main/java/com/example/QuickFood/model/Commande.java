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
import jakarta.persistence.CascadeType;

import java.util.List ;

import lombok.AllArgsConstructor;
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
    @JoinColumn(name = "livreur_id", nullable = true)
    private Livreur livreur;


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

    @ManyToMany
    @JoinTable(
            name = "commande_option", // Name of the join table
            joinColumns = @JoinColumn(name = "commande_id"), // Foreign key for Commande
            inverseJoinColumns = @JoinColumn(name = "option_id") // Foreign key for Option
    )
    private List<Option> options;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Paiement> paiements;

    // Default Constructor
    public Commande() {
    }

}