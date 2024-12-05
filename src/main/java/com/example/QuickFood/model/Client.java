package com.example.QuickFood.model;

import jakarta.persistence.*;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor
@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "idUtilisateur")
public class Client extends Utilisateur {

    @Column(unique = true)
    private String adresse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localisation_id", nullable = false)
    @NotNull
    private Localisation localisation;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Avis> avis;

    // Default Constructor
    public Client() {
        super();
    }
}
