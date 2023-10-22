package com.example.marche_et_besoin.Services;


import com.example.marche_et_besoin.Entities.Besoin;
import com.example.marche_et_besoin.Entities.Marche;
import com.example.marche_et_besoin.Repositories.BesoinRepository;
import com.example.marche_et_besoin.Repositories.MarcheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcheServiceIMP implements MarcheService{

    @Autowired
    private MarcheRepository marcheRepository;

    @Autowired
    private BesoinRepository besoinRepository;





    public List<Marche> getAllMarches() {
        return marcheRepository.findAll();
    }

    public Marche getMarcheById(Long id) {
        return marcheRepository.findById(id).orElse(null);
    }

    public Marche createMarche(Marche marche) {
        return marcheRepository.save(marche);
    }

    public Marche updateMarche(Long id, Marche marche) {
        Marche existingMarche = marcheRepository.findById(id).orElse(null);
        if (existingMarche != null) {
            existingMarche.setNom(marche.getNom());
            existingMarche.setRegion(marche.getRegion());
            existingMarche.setSecteur(marche.getSecteur());
            // Mettez à jour d'autres champs au besoin
            return marcheRepository.save(existingMarche);
        }
        return null;
    }

    public void deleteMarche(Long id) {
        marcheRepository.deleteById(id);
    }





    public List<Marche> searchMarkets(String keyword, String region, String secteur) {
        return marcheRepository.findByNomContainingAndRegionAndSecteur(keyword, region, secteur);
    }

    public List<Besoin> searchNeeds(String keyword) {
        return besoinRepository.findByDescriptionContaining(keyword);
    }

    public List<Marche> matchMarketsToNeeds(String keyword, String region, String secteur) {
        List<Marche> matchingMarkets = searchMarkets(keyword, region, secteur);
        List<Besoin> matchingNeeds = searchNeeds(keyword);

        // Your matching algorithm logic here...
        // For simplicity, let's assume a basic match where any common keyword leads to a match.

        // Example of a simple match
        for (Besoin besoin : matchingNeeds) {
            for (Marche marche : matchingMarkets) {
                if (marche.getSecteur().equalsIgnoreCase(besoin.getMarche().getSecteur())) {
                    marche.setMatchedNeed(besoin);
                    besoin.setMatchedMarket(marche);
                }
            }
        }

        return matchingMarkets;
    }

}
