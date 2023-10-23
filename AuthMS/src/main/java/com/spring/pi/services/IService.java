package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.payload.request.ClientRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IService {

    ////////////actor//////////////
    public List<Actor> getAllActor();
    public Actor getActorById(long id);
    public ResponseEntity<?> addActor(ClientRequest actor);

    public Actor updateActor(Actor actor);
    public void deleteActor(long id);




}
