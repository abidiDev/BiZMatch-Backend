package com.example.annonce_microservice.Entities;

import com.example.annonce_microservice.Enum.AnnonceType;
import com.example.annonce_microservice.Enum.Competence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceCollaboration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long identreprise;
    private String description;
    @Enumerated(EnumType.STRING)
    private Competence competences;

    @Enumerated(EnumType.STRING)
    private AnnonceType annonceType;
    private Date dateLimite;

    @OneToMany(mappedBy = "annonceCollaboration")
    private List<NotificationConfig> notificationConfigs;


}