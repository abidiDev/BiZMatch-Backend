package com.crm.crmms.entities;

import com.crm.crmms.models.Actor;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nom;
    private String description ;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    private Integer employeeNBR;

    private double income;
    @Enumerated(EnumType.STRING)

            //it's calculates based on the income of the entreprise
    Ecategory category;
    @Enumerated(EnumType.STRING)

    private Esecteur secteur;


    @ManyToOne
    private Service service;
    private Long ActorID;
    @Transient
    Actor actor;
}