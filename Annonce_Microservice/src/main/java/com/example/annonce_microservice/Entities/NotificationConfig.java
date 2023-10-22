package com.example.annonce_microservice.Entities;


import com.example.annonce_microservice.Enum.AnnonceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;  // ID de l'utilisateur associé à la configuration
    private AnnonceType annonceType;
    private boolean enableNotification;

    @ManyToOne
    @JoinColumn(name = "annonceCollaboration_id")
    private AnnonceCollaboration annonceCollaboration;

}
