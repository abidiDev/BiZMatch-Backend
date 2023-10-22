package com.example.marche_et_besoin.Repositories;

import com.example.marche_et_besoin.Entities.Marche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcheRepository extends JpaRepository<Marche, Long> {
    // Ajoute des méthodes spécifiques si

    List<Marche> findByNomContainingAndRegionAndSecteur(String nom, String region, String secteur);

}