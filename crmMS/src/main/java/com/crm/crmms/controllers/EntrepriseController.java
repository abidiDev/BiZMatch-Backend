package com.crm.crmms.controllers;


import com.crm.crmms.entities.Crm;
import com.crm.crmms.services.IcrmServive;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/crm")
public class EntrepriseController {
    IcrmServive icrmServive;


    @PostMapping("/createRelation")

    public ResponseEntity<?> createRelation(@RequestBody Crm createcrmRequest){
        return  icrmServive.createRelation(createcrmRequest);
    }
    @PostMapping("/changeRelationStatusTop")

    public ResponseEntity<?> changeRelationStatusTop(@RequestBody Crm updatecrmRequest){
        return icrmServive.changeRelationStatusTop(updatecrmRequest);
    }
    @PostMapping("/changeRelationStatusDown")

    public ResponseEntity<?> changeRelationStatusDown(@RequestBody Crm updatecrmRequest){
        return icrmServive.changeRelationStatusDown(updatecrmRequest);
    }
    @GetMapping("/relations/{idEntreprise}")
     @ResponseBody
    public ResponseEntity<?>getRelations(@PathVariable Integer idEntreprise){
        return icrmServive.getRelations(idEntreprise);

    }
    }
