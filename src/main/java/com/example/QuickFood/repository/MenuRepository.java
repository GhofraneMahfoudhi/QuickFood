package com.example.QuickFood.repository;

import com.example.QuickFood.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    //find all menus for a particular restaurant
    List<Menu> findByRestaurant_IdResto(int idResto);
}
