package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.payload.request.ClientRequest;
import com.spring.pi.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@AllArgsConstructor
public class ServiceImp implements IService{

    ActorRepository actorRepository;


    //////////////actor//////////////

    @Override
    public List<Actor> getAllActor() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getActorById(long id) {
        return actorRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> addActor(ClientRequest actor) {
       Actor a= new Actor();
       a.setFullName(actor.getFullname());
       a.setEmail(actor.getEmail());
       a.setPhone(actor.getPhone());

        return ResponseEntity.ok(actorRepository.save(a));

    }

    @Override
    public Actor updateActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(long id) {
         actorRepository.deleteById(id);

    }

}







