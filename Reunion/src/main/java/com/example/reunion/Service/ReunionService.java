package com.example.reunion.Service;

import com.example.reunion.Entity.Reunion;
import com.example.reunion.Repository.ReunionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReunionService implements IReunionService {
    @Autowired
    private ReunionRepository reunionRepository;

    @Override
    public Reunion createReunion(Reunion reunion){
        return reunionRepository.save(reunion);}


    @Override
    public Reunion getReunionById(Long reunionId){
        return reunionRepository.findById(reunionId).orElse(null);}

    @Override
        public List<Reunion> getAllReunions()
    {
        return reunionRepository.findAll();
    }
    @Override
    public Reunion updateReunion(Long reunionId, Reunion reunion){
        reunion.setReunionId(reunionId);
        return reunionRepository.save(reunion);

    }

    @Override
    public void deleteReunion(Long reunionId) {
        reunionRepository.deleteById(reunionId);

    }
    @Override
    public List<Reunion> searchReunionBykeyword(String keyword){
        return reunionRepository.findByAgendaContainingIgnoreCase(keyword);
    }


}
