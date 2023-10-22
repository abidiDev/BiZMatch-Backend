package com.crm.crmms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "entreprise_porteuse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntreprisePorteuse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    private Entreprise entreprise1;
    @ManyToOne

    private Entreprise entreprise2;

    @Enumerated(EnumType.STRING)

    private EPartnershipType partnershipType;
    @Enumerated(EnumType.STRING)

    private EStatus status;


}