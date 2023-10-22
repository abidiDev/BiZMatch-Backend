package com.crm.crmms.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nom;
    private String description ;
    private double discount;
    @Enumerated(EnumType.STRING)
    private Eservicequality servicequality;




}