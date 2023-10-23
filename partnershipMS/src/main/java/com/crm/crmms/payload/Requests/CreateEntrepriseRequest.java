package com.crm.crmms.payload.Requests;

import com.crm.crmms.entities.Esecteur;
import com.crm.crmms.entities.Eservicequality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CreateEntrepriseRequest {
    private String nom;
    private String description ;
    private Date creationDate;
    private Integer employeeNBR;

    private double income;
    private Esecteur secteur;

    private String nomS;
    private String descriptionS ;
    private double discount;
    private Eservicequality servicequality;
    private Long ActorId;

}
