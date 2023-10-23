package com.crm.crmms.repositories;

import com.crm.crmms.entities.Entreprise;
import com.crm.crmms.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}