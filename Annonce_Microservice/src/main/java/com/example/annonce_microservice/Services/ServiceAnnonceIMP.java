package com.example.annonce_microservice.Services;


import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Competence;
import com.example.annonce_microservice.Repositories.AnnonceCollaborationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public  class ServiceAnnonceIMP implements ServiceAnnonce{

    @Autowired
    AnnonceCollaborationRepository annonceCollaborationRepository;

    public ResponseEntity<?> getAllAnnonces() {
        return ResponseEntity.ok(annonceCollaborationRepository.findAll());


    }

    public AnnonceCollaboration getAnnonceById(Long id) {
        return annonceCollaborationRepository.findById(id).orElse(null);
    }

    public AnnonceCollaboration createAnnonce(AnnonceCollaboration annonce) {
        return annonceCollaborationRepository.save(annonce);
    }

    public AnnonceCollaboration updateAnnonce(Long id, AnnonceCollaboration nouvelleAnnonce) {
        AnnonceCollaboration annonceExistante = annonceCollaborationRepository.findById(id).orElse(null);

        if (annonceExistante != null) {
            // Met à jour les champs nécessaires
            annonceExistante.setIdentreprise(nouvelleAnnonce.getIdentreprise());
            annonceExistante.setDescription(nouvelleAnnonce.getDescription());
            annonceExistante.setCompetences(nouvelleAnnonce.getCompetences());
            annonceExistante.setDateLimite(nouvelleAnnonce.getDateLimite());

            return annonceCollaborationRepository.save(annonceExistante);
        } else {
            return null; // L'annonce n'existe pas
        }
    }
    public void deleteAnnonce(Long id) {
        annonceCollaborationRepository.deleteById(id);
    }



    public List<AnnonceCollaboration> searchByCompetences(Competence competence) {
        // Recherche les annonces qui correspondent à la compétence spécifiée
        return annonceCollaborationRepository.findByCompetences(competence);
    }



    public void deleteExpiredAnnouncements() {
        Date currentDate = new Date();
        List<AnnonceCollaboration> expiredAnnouncements = annonceCollaborationRepository.findByDateLimiteBefore(currentDate);

        for (AnnonceCollaboration annonce : expiredAnnouncements) {
            // You can perform additional actions before deletion if needed
            annonceCollaborationRepository.delete(annonce);
        }
    }

}
