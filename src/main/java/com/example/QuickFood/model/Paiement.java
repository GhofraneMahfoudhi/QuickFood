package com.example.QuickFood.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor

@Table(name = "paiements")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaiement;

    @Column(name = "montant", nullable = false)
    private float montant;

    @ManyToOne
    @JoinColumn(name = "livreur_id", nullable = false) // Foreign key to Livreur
    private Livreur livreur;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false) // Foreign key to Commande
    private Commande commande;

    public Paiement() {
    }
}
