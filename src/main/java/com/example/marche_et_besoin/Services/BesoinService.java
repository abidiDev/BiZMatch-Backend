package com.example.marche_et_besoin.Services;

import com.example.marche_et_besoin.Entities.Besoin;

import java.util.List;

public interface BesoinService {

    List<Besoin> getAllBesoins();
    Besoin getBesoinById(Long id);
    Besoin createBesoin(Besoin besoin);
    Besoin updateBesoin(Long id, Besoin besoin);
    void deleteBesoin(Long id);
}
