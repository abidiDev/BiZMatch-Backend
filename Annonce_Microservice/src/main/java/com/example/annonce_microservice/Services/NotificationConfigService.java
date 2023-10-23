package com.example.annonce_microservice.Services;


import com.example.annonce_microservice.Entities.NotificationConfig;
import com.example.annonce_microservice.Enum.AnnonceType;
import com.example.annonce_microservice.Repositories.NotificationConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConfigService {


    @Autowired
    private NotificationConfigRepository notificationConfigRepository;

    public void configureNotification(String userId, AnnonceType annonceType, boolean enableNotification) {
        NotificationConfig config = notificationConfigRepository.findByUserIdAndAnnonceType(userId, annonceType);
        if (config == null) {
            config = new NotificationConfig();
            config.setUserId(userId);
            config.setAnnonceType(annonceType);
        }
        config.setEnableNotification(enableNotification);
        notificationConfigRepository.save(config);
    }

    public boolean isNotificationEnabled(String userId, AnnonceType annonceType) {
        NotificationConfig config = notificationConfigRepository.findByUserIdAndAnnonceType(userId, annonceType);
        return config != null && config.isEnableNotification();
    }
}
