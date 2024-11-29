package com.example.QuickFood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor
@Entity

public class Client extends Utilisateur {

    private String adresse;
    @ManyToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    @OneToMany(mappedBy = "client")
    private List<Avis> avis;

    // Default Constructor
    public Client() {
    }
}
