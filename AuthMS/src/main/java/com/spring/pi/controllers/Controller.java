package com.spring.pi.controllers;

import com.spring.pi.entities.*;
import com.spring.pi.repositories.*;
import com.spring.pi.services.IService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/CRUD")
@CrossOrigin(origins = "*", maxAge = 3600)


public class Controller {
    IService iService;

////////////////////actor///////////
@GetMapping("/getAllActor")
@ResponseBody
    public List<Actor> getAllActor(){return iService.getAllActor();}
    @GetMapping("/getActorById/{id}")
    @ResponseBody
    public Actor getActorById(@PathVariable long id){return iService.getActorById(id);}

    @PutMapping("/updateActor")
    @ResponseBody
    public Actor updateActor(@RequestBody Actor actor){return iService.updateActor(actor);}
    @DeleteMapping("/deleteActor/{id}")
    @ResponseBody
    public void deleteActor(@PathVariable long id){iService.deleteActor(id);}
    //////////////////conversation////////////////////////



}



