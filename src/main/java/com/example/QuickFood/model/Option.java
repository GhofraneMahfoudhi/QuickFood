package com.example.QuickFood.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor

@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    private int idOption;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeOption types; // Enumeration type

    @Column(name = "prix", nullable = false)
    private float prix;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "options")
    private List<Commande> commandes;

    public Option() {
    }
}
