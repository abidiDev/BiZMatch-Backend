package com.example.annonce_microservice.Repositories;

import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface AnnonceCollaborationRepository extends JpaRepository<AnnonceCollaboration, Long> {

    List<AnnonceCollaboration> findByCompetences(Competence competence);
    List<AnnonceCollaboration> findByDateLimiteBefore(Date currentDate);

}

