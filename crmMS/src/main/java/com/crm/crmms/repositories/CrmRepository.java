package com.crm.crmms.repositories;

import com.crm.crmms.entities.Crm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrmRepository extends JpaRepository<Crm, Integer> {
}