package com.example.reunion.Controller;

import com.example.reunion.Entity.Reunion;
import com.example.reunion.Repository.ReunionRepository;
import com.example.reunion.Service.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reunion")
@CrossOrigin("*")
public class ReunionController {
    @Autowired
    private ReunionService reunionService;

    @PostMapping
    public Reunion createReunion(@RequestBody Reunion reunion ){
        return reunionService.createReunion(reunion);
    }

    @GetMapping("retrieve/{reunionId}")
    public Reunion getReunionById(@PathVariable Long reunionId){
        return reunionService.getReunionById(reunionId);
    }
    @GetMapping("/retrieveall")
    public List<Reunion> getAllReunions(){return reunionService.getAllReunions(); }

    @PutMapping("/update/{reunionId}")
    public Reunion updateReunion(@PathVariable Long reunionId, @RequestBody Reunion  reunion){
        return reunionService.updateReunion(reunionId,reunion);
    }
    @DeleteMapping("/delete/{reunionId}")
    public void deleteReunion(@PathVariable Long reunionId){
        reunionService.deleteReunion(reunionId);

    }
   @GetMapping("/search")
     public List<Reunion> searchReunionBykeyword(@RequestParam (name = "keyword")String keyword) { return reunionService.searchReunionBykeyword(keyword); }
}