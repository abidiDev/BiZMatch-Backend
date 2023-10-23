package com.crm.crmms.services;

import com.crm.crmms.entities.Crm;
import org.springframework.http.ResponseEntity;

public interface IcrmServive {
    public ResponseEntity<?> createRelation(Crm createcrmRequest);

    public ResponseEntity<?> changeRelationStatusTop(Crm updatecrmRequest);
    public ResponseEntity<?> changeRelationStatusDown(Crm updatecrmRequest);
    public ResponseEntity<?>getRelations(Integer idEntreprise);





}
