package com.example.QuickFood.repository;


import com.example.QuickFood.model.Repas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepasRepository extends JpaRepository<Repas, Integer> {
}
