package com.example.marche_et_besoin.Services;

import com.example.marche_et_besoin.Entities.Besoin;
import com.example.marche_et_besoin.Repositories.BesoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BesoinServiceIMP implements  BesoinService{

    @Autowired
    private BesoinRepository besoinRepository;
    public List<Besoin> getAllBesoins() {
        return besoinRepository.findAll();
    }

    public Besoin getBesoinById(Long id) {
        return besoinRepository.findById(id).orElse(null);
    }

    public Besoin createBesoin(Besoin besoin) {
        return besoinRepository.save(besoin);
    }

    public Besoin updateBesoin(Long id, Besoin besoin) {
        Besoin existingBesoin = besoinRepository.findById(id).orElse(null);
        if (existingBesoin != null) {
            existingBesoin.setDescription(besoin.getDescription());
            // Mettez à jour d'autres champs au besoin
            return besoinRepository.save(existingBesoin);
        }
        return null;
    }

    public void deleteBesoin(Long id) {
        besoinRepository.deleteById(id);
    }

    public List<Besoin> searchNeeds(String keyword) {
        return besoinRepository.findByDescriptionContaining(keyword);
    }
}
