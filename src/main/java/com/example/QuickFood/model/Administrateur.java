package com.example.QuickFood.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity

public class Administrateur extends Utilisateur {

    // Constructors
    public Administrateur() {
        super();
    }

}

