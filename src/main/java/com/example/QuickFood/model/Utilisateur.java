package com.example.QuickFood.model;

import jakarta.persistence.*;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@Setter
@AllArgsConstructor // Generates an all-arguments constructor


@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtilisateur;

    @Column(name = "nomu")
    @NotNull
    private String nomU;

    @Column(name = "prenom")
    @NotNull
    private String prenom;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "mot_de_passe")
    @NotNull
    private String motDePasse;

    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commande> commandes;

    // Default Constructor
    public Utilisateur() {
    }

    public Utilisateur(String nomU, String prenom, String email, String motDePasse) {
        this.nomU = nomU;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse =hashPassword(motDePasse);
    }

    public String hashPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, this.motDePasse);
    }

}