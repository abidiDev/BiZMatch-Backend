package com.example.marche_et_besoin.Services;


import com.example.marche_et_besoin.Entities.Besoin;
import com.example.marche_et_besoin.Entities.Marche;

import java.util.List;

public interface MarcheService {

    List<Marche> getAllMarches();
    Marche getMarcheById(Long id);
    Marche createMarche(Marche marche);
    Marche updateMarche(Long id, Marche marche);
    void deleteMarche(Long id);
    List<Marche> matchMarketsToNeeds(String keyword, String region, String secteur);
    List<Besoin> searchNeeds(String keyword);

    List<Marche> searchMarkets(String keyword, String region, String secteur);
}
