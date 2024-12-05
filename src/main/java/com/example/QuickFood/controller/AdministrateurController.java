package com.example.QuickFood.controller;

import com.example.QuickFood.model.Repas;
import com.example.QuickFood.model.Restaurant;
import com.example.QuickFood.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/administrateur")
public class AdministrateurController {

    private final AdministrateurService administrateurService;

    @Autowired
    public AdministrateurController(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    @PostMapping("/restos")
    public void gererRestos(@RequestParam String action, @RequestBody Restaurant restaurant) {
        administrateurService.gererRestos(action, restaurant);
    }

    @PostMapping("/repas")
    public void gererRepas(@RequestParam String action, @RequestBody Repas repas) {
        administrateurService.gererRepas(action, repas);
    }

    @PostMapping("/commandes")
    public void gererCommandes(@RequestParam String action, @RequestParam int idCommande, @RequestParam int restaurantId) {
        administrateurService.gererCommandes(action, idCommande, restaurantId);
    }

    @DeleteMapping("/avis/{idAvis}")
    public void gererAvis(@PathVariable int idAvis) {
        administrateurService.gererAvis("delete", idAvis);
    }

    @PostMapping("/attribuerLivreur")
    public void attribuerLivreur(@RequestParam int idCommande, @RequestParam int idLivreur) {
        administrateurService.attribuerLivreur(idCommande, idLivreur);
    }
}

