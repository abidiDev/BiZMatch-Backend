package com.example.reunion.Repository;

import com.example.reunion.Entity.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ReunionRepository extends JpaRepository<Reunion,Long> {
    List<Reunion>findByAgendaContainingIgnoreCase(String keyword);


}
