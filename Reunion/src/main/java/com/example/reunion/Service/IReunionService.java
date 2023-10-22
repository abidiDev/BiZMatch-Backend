package com.example.reunion.Service;

import com.example.reunion.Entity.Reunion;

import java.util.List;

public interface IReunionService {
    Reunion createReunion(Reunion reunion);
    Reunion getReunionById (Long reunionId);
    List<Reunion> getAllReunions();
    Reunion updateReunion (Long reunionId ,Reunion reunion);
    void deleteReunion (Long reunionId);
    List<Reunion> searchReunionBykeyword(String keyword);




}
