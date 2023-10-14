package com.example.business_needs_matchmaker.Service;


import com.example.business_needs_matchmaker.Repository.BusinessNRepo;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BusinessSI implements BusinessService{


    @Autowired
    private BusinessNRepo businessNRepo;

}
