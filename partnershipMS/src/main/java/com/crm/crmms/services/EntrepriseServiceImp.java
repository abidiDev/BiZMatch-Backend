package com.crm.crmms.services;

import com.crm.crmms.entities.Entreprise;
import com.crm.crmms.entities.EntreprisePorteuse;
import com.crm.crmms.entities.Esecteur;
import com.crm.crmms.payload.Requests.CreateEntrepriseRequest;
import com.crm.crmms.payload.Requests.PartnershipCreationRequest;
import com.crm.crmms.payload.Response.MessageResponse;
import com.crm.crmms.payload.Response.PartnershipResponse;
import com.crm.crmms.repositories.EntreprisePorteuseRepository;
import com.crm.crmms.repositories.EntrepriseRepository;
import com.crm.crmms.repositories.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.crm.crmms.entities.EStatus.ACCEPTED;
import static com.crm.crmms.entities.EStatus.NEW;
import static com.crm.crmms.entities.Ecategory.*;

@Service
@AllArgsConstructor
@Getter
@Setter
@EnableFeignClients
public class EntrepriseServiceImp implements IEntrepriseService {
    private EntrepriseRepository entrepriseRepository;
    private ServiceRepository serviceRepository;
    private  EntreprisePorteuseRepository entreprisePorteuseRepository;

    private ActorRestClient actorRestClient;

    @Override
    public ResponseEntity<?> createEntreprise(CreateEntrepriseRequest createEntrepriseRequest) {
        //createEntreprise
        Entreprise e=new Entreprise();
        e.setNom(createEntrepriseRequest.getNom());
        e.setDescription(createEntrepriseRequest.getDescription());
        e.setEmployeeNBR(createEntrepriseRequest.getEmployeeNBR());
        e.setCreationDate(createEntrepriseRequest.getCreationDate());
        e.setIncome(createEntrepriseRequest.getIncome());
        e.setSecteur(createEntrepriseRequest.getSecteur());
        //detecting class of Entreprise
        if(createEntrepriseRequest.getIncome()<50000){
            e.setCategory(STRUGGLING);

        } else if (createEntrepriseRequest.getIncome() < 500000 && createEntrepriseRequest.getIncome() > 50000) {
            e.setCategory(MODERATE_WEALTH);

        }
        else if (createEntrepriseRequest.getIncome() > 500000) {
            e.setCategory(WEALTHY);

        }

        e=entrepriseRepository.save(e);
        //createService
        com.crm.crmms.entities.Service s= new com.crm.crmms.entities.Service();
        s.setNom(createEntrepriseRequest.getNomS());
        s.setDescription(createEntrepriseRequest.getDescriptionS());
        s.setDiscount(createEntrepriseRequest.getDiscount());
        s.setServicequality(createEntrepriseRequest.getServicequality());
        s=serviceRepository.save(s);
        //joining with client
        e.setActorID(createEntrepriseRequest.getActorId());
        //joining
        e.setService(s);
        entrepriseRepository.save(e);


        return ResponseEntity.ok(
                e
        );
    }
    public ResponseEntity<?>EntrepriseByUser(Long userID) {
        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        for (Entreprise ep : entrepriseRepository.findAll()
        ) {
            if (ep.getActorID() == userID) {
                entreprises.add(ep);

            }

        }
        return ResponseEntity.ok(
                entreprises
        );
    }

        @Override
        public ResponseEntity<?> AllEntreprises () {
            return ResponseEntity.ok(
                    entrepriseRepository.findAll()
            );
        }


    @Override
    public ResponseEntity<?> PertnentEntreprises() {
        return null;
    }
    public ResponseEntity<?> EntreprisebyId(Integer Id){
        return ResponseEntity.ok(
                entrepriseRepository.findById(Id).orElse(null)
        );
    }


    @Override
    public ResponseEntity<?> EntreprisebyEmployeenbr(Integer emplyeeNbr) {
        return ResponseEntity.ok(
                entrepriseRepository.findByEmployeeNBR(emplyeeNbr)
        );
    }

    @Override
    public ResponseEntity<?> EntreprisebyCreationDate(Date creationDate) {
        return ResponseEntity.ok(
                entrepriseRepository.findByCreationDate(creationDate)
        );
    }

    public ResponseEntity<?> EntreprisebySecteur(Esecteur esecteur){

        return ResponseEntity.ok(
                entrepriseRepository.findBySecteur(esecteur)
        );

    }
    public ResponseEntity<?> EntreprisebyService(String serviceName){
        return ResponseEntity.ok(
                entrepriseRepository.findByServiceNom(serviceName)
        );


    }

    @Override
    public ResponseEntity<?> createPartnership(PartnershipCreationRequest partnershipCreationRequest) {

        Entreprise e1=entrepriseRepository.findById(partnershipCreationRequest.getIdEntreprise1()).orElse(null);
        Entreprise e2=entrepriseRepository.findById(partnershipCreationRequest.getIdEntreprise2()).orElse(null);
        EntreprisePorteuse ep=new EntreprisePorteuse();
        ep.setEntreprise1(e1);
        ep.setPartnershipType(partnershipCreationRequest.getPartnershipType());
        ep.setEntreprise2(e2);
        ep.setStatus(NEW);
        entreprisePorteuseRepository.save(ep);
        return ResponseEntity.ok(new MessageResponse("Partnership created successfully!"));



    }

    @Override
    public ResponseEntity<?> getPartnership(Integer idEntreprise) {
        List<PartnershipResponse> partners = new ArrayList<PartnershipResponse>();
        List<EntreprisePorteuse> porteuse = entreprisePorteuseRepository.findAll();
        for (EntreprisePorteuse ep : porteuse
        ) {
            //requestedPartnership
            if (ep.getEntreprise1().getId() == idEntreprise) {
                partners.add(new PartnershipResponse(ep.getEntreprise2(),ep.getPartnershipType(),"Requested",ep.getStatus()));

            }
            //recievedPartnership

            else if (ep.getEntreprise2().getId() == idEntreprise) {
                partners.add(new PartnershipResponse(ep.getEntreprise1(),ep.getPartnershipType(),"ToProvide",ep.getStatus()));


            }

        }
        return ResponseEntity.ok(
                partners
        );
    }


    public ResponseEntity<?> acceptPartnership(Integer idE1,Integer idE2){
        List<EntreprisePorteuse> porteuse = entreprisePorteuseRepository.findAll();
        for (EntreprisePorteuse ep : porteuse
        ) {
            if (ep.getEntreprise1().getId() == idE1) {
                if(ep.getEntreprise2().getId() == idE2) {
                    ep.setStatus(ACCEPTED);
                    entreprisePorteuseRepository.save(ep);
                }
            }

            if (ep.getEntreprise1().getId() == idE2) {
                if(ep.getEntreprise2().getId() == idE1) {
                    ep.setStatus(ACCEPTED);
                    entreprisePorteuseRepository.save(ep);
                }
            }

        }

        return ResponseEntity.ok(new MessageResponse("Partnership deleted successfully!"));

    }

    public ResponseEntity<?> removePartnership(Integer idE1,Integer idE2){
        List<EntreprisePorteuse> porteuse = entreprisePorteuseRepository.findAll();
        for (EntreprisePorteuse ep : porteuse
        ) {
            if (ep.getEntreprise1().getId() == idE1) {
                if(ep.getEntreprise2().getId() == idE2) {
                    entreprisePorteuseRepository.delete(ep);
                }
            }

            if (ep.getEntreprise1().getId() == idE2) {
                if(ep.getEntreprise2().getId() == idE1) {
                    entreprisePorteuseRepository.delete(ep);
                }
            }

            }

        return ResponseEntity.ok(new MessageResponse("Partnership deleted successfully!"));

    }
    
    
}
