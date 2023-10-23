package com.crm.crmms.services;

import com.crm.crmms.entities.Crm;
import com.crm.crmms.entities.Estatus;
import com.crm.crmms.payload.Response.RelationR;
import com.crm.crmms.repositories.CrmRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Getter
@Setter
@EnableFeignClients
public class crmServiceImpl implements IcrmServive {
    CrmRepository crmRepository;
    ActorRestClient actorRestClient;

    @Override
    public ResponseEntity<?> createRelation(Crm createcrmRequest) {
        return ResponseEntity.ok(
                crmRepository.save(createcrmRequest)
        );
    }

    @Override
    public ResponseEntity<?> changeRelationStatusTop(Crm updatecrmRequest) {
        switch (updatecrmRequest.getStatus()) {
            case PROPOSED:
                updatecrmRequest.setStatus(Estatus.WON);
                break;

            case NEW:
                updatecrmRequest.setStatus(Estatus.PROPOSED);


                break;
            default:

        }


        return ResponseEntity.ok(
                crmRepository.save(updatecrmRequest)
        );    }

    @Override
    public ResponseEntity<?> changeRelationStatusDown(Crm updatecrmRequest) {
        switch (updatecrmRequest.getStatus()) {
            case PROPOSED:
                updatecrmRequest.setStatus(Estatus.NEW);
                break;

            case WON:
                updatecrmRequest.setStatus(Estatus.PROPOSED);


                break;
            default:
        }



        return ResponseEntity.ok(
                crmRepository.save(updatecrmRequest)
        );     }

    @Override
    public ResponseEntity<?> getRelations(Integer idEntreprise) {
        List<RelationR> Rlist=new ArrayList<RelationR>();
        for (Crm relation : crmRepository.findAll()
        ) {
            if (relation.getIdEntreprise() == idEntreprise) {
                RelationR relationr=new RelationR(actorRestClient.findActorById(relation.getIdclient()), relation.getStatus(),relation.getId());
                Rlist.add(relationr);

            }

        }
        return ResponseEntity.ok(
                Rlist       );
    }
}
