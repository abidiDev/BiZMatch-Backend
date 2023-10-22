package com.example.marche_et_besoin.Controllers;


import com.example.marche_et_besoin.Entities.Besoin;
import com.example.marche_et_besoin.Entities.Marche;
import com.example.marche_et_besoin.Services.BesoinService;
import com.example.marche_et_besoin.Services.MarcheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marches")
public class MarcheController {

    @Autowired
    private MarcheService marcheService;
    @Autowired
    BesoinService besoinService;

    @GetMapping("/getall")
    public List<Marche> getAllMarches() {
        return marcheService.getAllMarches();
    }

    @GetMapping("/getbyid/{id}")
    public Marche getMarcheById(@PathVariable Long id) {
        return marcheService.getMarcheById(id);
    }

    @PostMapping("/create")
    public Marche createMarche(@RequestBody Marche marche) {
        return marcheService.createMarche(marche);
    }

    @PutMapping("/update/{id}")
    public Marche updateMarche(@PathVariable Long id, @RequestBody Marche marche) {
        return marcheService.updateMarche(id, marche);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMarche(@PathVariable Long id) {
        marcheService.deleteMarche(id);
    }

    @GetMapping("/search")
    public List<Marche> searchMarkets(@RequestParam String keyword, @RequestParam String region, @RequestParam String secteur) {
        return marcheService.searchMarkets(keyword, region, secteur);
    }

    @GetMapping("/match")
    public List<Marche> matchMarketsToNeeds(@RequestParam String keyword, @RequestParam String region, @RequestParam String secteur) {
        return marcheService.matchMarketsToNeeds(keyword, region, secteur);
    }

    @GetMapping("/search_needs")
    public List<Besoin> searchNeeds(@RequestParam String keyword) {
        return marcheService.searchNeeds(keyword);
    }
}
