package com.example.annonce_microservice.Controllers;

import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Competence;
import com.example.annonce_microservice.Services.ServiceAnnonce;
import com.example.annonce_microservice.Services.ServiceAnnonceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/annonces")
@CrossOrigin(origins = "*", maxAge = 3600)


public class AnnonceController {

    @Autowired
    public ServiceAnnonce annonceService;



    @GetMapping("/getAllAnnonces")
    public ResponseEntity<?> getAllAnnonces() {
        return annonceService.getAllAnnonces();
    }

    @GetMapping("/getAnnoncesById/{id}")
    public AnnonceCollaboration getAnnonceById(@PathVariable Long id) {
        return annonceService.getAnnonceById(id);
    }

    @PostMapping("/creatAnnonce")
    public AnnonceCollaboration createAnnonce(@RequestBody AnnonceCollaboration annonce) {
        return annonceService.createAnnonce(annonce);
    }

    @PutMapping("/updateAnnonce/{id}")
    public AnnonceCollaboration updateAnnonce(@PathVariable Long id, @RequestBody AnnonceCollaboration nouvelleAnnonce) {
        return annonceService.updateAnnonce(id, nouvelleAnnonce);
    }


    @DeleteMapping("/DeleteAnnonce/{id}")
    public void deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
    }

    @GetMapping("/searchAnnonce/competences/{competence}")
    public List<AnnonceCollaboration> searchByCompetences(@PathVariable Competence competence) {
        return annonceService.searchByCompetences(competence);

}

    @Scheduled(cron = "0 0 0 * * *")
    //@Scheduled(cron = "0/1 * * * * *")
    public void deleteExpiredAnnouncements() {
        annonceService.deleteExpiredAnnouncements();
    }

}
