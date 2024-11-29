package com.example.QuickFood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
// Generates Getters, Setters, equals(), hashCode(), toString(), etc.
@AllArgsConstructor // Generates an all-arguments constructor

@Table(name = "avis")
public class Avis {

    @Id
    private int idAvis;

    @Column(name = "note", nullable = false)
    private int note;

    @Column(name = "commentaire", nullable = true, length = 500)
    private String commentaire;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "temps", nullable = false)
    private Time temps;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "idResto")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "idUtilisateur")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "repas_id", referencedColumnName = "idRepas")
    private Repas repas;

    // Default Constructor
    public Avis() {
    }

}
