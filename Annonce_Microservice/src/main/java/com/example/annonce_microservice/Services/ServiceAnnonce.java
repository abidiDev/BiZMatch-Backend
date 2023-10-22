package com.example.annonce_microservice.Services;

import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Competence;

import java.util.List;
import java.util.Set;

public interface ServiceAnnonce {
    List<AnnonceCollaboration> getAllAnnonces();
    AnnonceCollaboration getAnnonceById(Long id);
    AnnonceCollaboration createAnnonce(AnnonceCollaboration annonce);

    AnnonceCollaboration updateAnnonce(Long id, AnnonceCollaboration nouvelleAnnonce);

    void deleteAnnonce(Long id);

    void deleteExpiredAnnouncements();

    List<AnnonceCollaboration> searchByCompetences(Competence competence);
}
