package com.example.annonce_microservice.Repositories;

import com.example.annonce_microservice.Entities.NotificationConfig;
import com.example.annonce_microservice.Enum.AnnonceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationConfigRepository extends JpaRepository<NotificationConfig,Long> {

    NotificationConfig findByUserIdAndAnnonceType(String userId, AnnonceType annonceType);

}
