package com.example.marche_et_besoin.Repositories;

import com.example.marche_et_besoin.Entities.Besoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Long> {
    // Ajoute des méthodes spécifiques si nécessaire
    List<Besoin> findByDescriptionContaining(String description);

}