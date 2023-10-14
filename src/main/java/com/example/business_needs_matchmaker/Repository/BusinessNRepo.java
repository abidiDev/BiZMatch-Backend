package com.example.business_needs_matchmaker.Repository;

import com.example.business_needs_matchmaker.Entities.BusinessNeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessNRepo extends JpaRepository<BusinessNeed, Long> {


}
