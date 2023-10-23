package com.crm.crmms.controllers;

import com.crm.crmms.entities.Entreprise;
import com.crm.crmms.entities.Esecteur;
import com.crm.crmms.payload.Requests.CreateEntrepriseRequest;
import com.crm.crmms.payload.Requests.PartnershipCreationRequest;
import com.crm.crmms.repositories.EntrepriseRepository;
import com.crm.crmms.services.IEntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {
    IEntrepriseService iEntrepriseService;
    private final EntrepriseRepository entrepriseRepository;
    @PostMapping("/createEntreprise")


    public ResponseEntity<?> createEntreprise(@RequestBody CreateEntrepriseRequest createEntrepriseRequest)
        { return iEntrepriseService.createEntreprise(createEntrepriseRequest) ;}
    @GetMapping("/EntrepriseByUser/{userID}")
    @ResponseBody
    public ResponseEntity<?>EntrepriseByUser(@PathVariable  Long userID) {
        return iEntrepriseService.EntrepriseByUser(userID);
    }
    @GetMapping("/AllEnytreprises")
    @ResponseBody
    public ResponseEntity<?> AllEntreprises(){ return iEntrepriseService.AllEntreprises() ;}
    @GetMapping("/ByemployeeNbr/{emplyeeNbr}")
    @ResponseBody
    public ResponseEntity<?> EntreprisebyEmployeenbr(@PathVariable("emplyeeNbr") Integer emplyeeNbr){
        return iEntrepriseService.EntreprisebyEmployeenbr(emplyeeNbr) ;}
    @GetMapping("/ById/{Id}")
    @ResponseBody
    public ResponseEntity<?> EntreprisebyId(@PathVariable Integer Id) {
        return iEntrepriseService.EntreprisebyId(Id);
    }
        @GetMapping("/BycreationDate/{creationDate}")

    public ResponseEntity<?> EntreprisebyCreationDate(@PathVariable("creationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date creationDate){ return iEntrepriseService.EntreprisebyCreationDate(creationDate) ;}
    @GetMapping("/Bysecteur/{esecteur}")

    public ResponseEntity<?> EntreprisebySecteur(@PathVariable Esecteur esecteur){

        return iEntrepriseService.EntreprisebySecteur(esecteur);

    }
    @GetMapping("/Byservice/{serviceName}")

    public ResponseEntity<?> EntreprisebyService(@PathVariable String serviceName){
        return iEntrepriseService.EntreprisebyService(serviceName);

    }
    @PostMapping("/createPartnership")

    public ResponseEntity<?> createPartnership(@RequestBody PartnershipCreationRequest partnershipCreationRequest ) {
        return iEntrepriseService.createPartnership(partnershipCreationRequest) ;}
    @GetMapping("/getPartnership/{idEntreprise}")

    public ResponseEntity<?> getPartnership(@PathVariable Integer idEntreprise) {
        return  iEntrepriseService.getPartnership(idEntreprise);
    }
    @GetMapping ("/acceptPartnership/{idE1}/{idE2}")

    public ResponseEntity<?> acceptPartnership(@PathVariable Integer idE1,@PathVariable Integer idE2){
        return iEntrepriseService.acceptPartnership(idE1, idE2);

    }

    @DeleteMapping ("/deletePartnership/{idE1}/{idE2}")

    public ResponseEntity<?> removePartnership(@PathVariable Integer idE1, @PathVariable Integer idE2) {
        return iEntrepriseService.removePartnership(idE1,idE2);
    }

    }
