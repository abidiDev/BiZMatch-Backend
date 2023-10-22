package com.crm.crmms.services;

import com.crm.crmms.entities.Entreprise;
import com.crm.crmms.entities.Esecteur;
import com.crm.crmms.payload.Requests.CreateEntrepriseRequest;
import com.crm.crmms.payload.Requests.PartnershipCreationRequest;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface IEntrepriseService {
    public ResponseEntity<?> createEntreprise(CreateEntrepriseRequest createEntrepriseRequest);

    public ResponseEntity<?> AllEntreprises();
    public ResponseEntity<?>PertnentEntreprises();
    public ResponseEntity<?>EntrepriseByUser(Long userID);
    public ResponseEntity<?> EntreprisebyId(Integer Id);

    public ResponseEntity<?> EntreprisebyEmployeenbr(Integer emplyeeNbr);
    public ResponseEntity<?> EntreprisebyCreationDate(Date creationDate);
    public ResponseEntity<?> EntreprisebySecteur(Esecteur esecteur);
    public ResponseEntity<?> EntreprisebyService(String serviceName);

    public ResponseEntity<?> createPartnership(PartnershipCreationRequest partnershipCreationRequest);
    public ResponseEntity<?>getPartnership(Integer idEntreprise);

    public ResponseEntity<?> acceptPartnership(Integer idE1,Integer idE2);
    public ResponseEntity<?> removePartnership(Integer idE1,Integer idE2);




}
