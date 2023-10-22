package com.example.marche_et_besoin.Controllers;

import com.example.marche_et_besoin.Entities.Besoin;
import com.example.marche_et_besoin.Services.BesoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/besoins")

public class BesoinController {

    @Autowired
    private BesoinService besoinService;

    @GetMapping("/gettAll")
    public List<Besoin> getAllBesoins() {
        return besoinService.getAllBesoins();
    }

    @GetMapping("getbyid/{id}")
    public Besoin getBesoinById(@PathVariable Long id) {
        return besoinService.getBesoinById(id);
    }

    @PostMapping("/create")
    public Besoin createBesoin(@RequestBody Besoin besoin) {
        return besoinService.createBesoin(besoin);
    }

    @PutMapping("/update/{id}")
    public Besoin updateBesoin(@PathVariable Long id, @RequestBody Besoin besoin) {
        return besoinService.updateBesoin(id, besoin);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBesoin(@PathVariable Long id) {
        besoinService.deleteBesoin(id);

    }
}