package com.example.QuickFood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "administrateur")
@PrimaryKeyJoinColumn(name = "id_utilisateur")
public class Administrateur extends Utilisateur {

    // Constructors
    public Administrateur() {
        super();
    }

}

