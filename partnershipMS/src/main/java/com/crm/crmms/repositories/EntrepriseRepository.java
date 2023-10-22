package com.crm.crmms.repositories;

import com.crm.crmms.entities.Entreprise;
import com.crm.crmms.entities.Esecteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Optional<Entreprise> findByEmployeeNBR(Integer employeeNbr);
    Optional<Entreprise> findByCreationDate (Date creationDate);
    Optional<Entreprise> findBySecteur (Esecteur secteur);
    Optional<Entreprise> findByServiceNom (String serviceName);



}