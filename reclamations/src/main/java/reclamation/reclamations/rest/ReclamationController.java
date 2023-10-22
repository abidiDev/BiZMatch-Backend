package reclamation.reclamations.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reclamation.reclamations.mail.EmailControllers;
import reclamation.reclamations.model.actor;
import reclamation.reclamations.persistence.Reclamation;
import reclamation.reclamations.services.Interface.ReclamationService;

import java.util.Arrays;
import java.util.List;
//import org.apache.commons.validator.routines.EmailValidator;

@RestController
@RequestMapping("/reclamation")
@AllArgsConstructor
public class ReclamationController {
    @Autowired
    ReclamationService reclamationService;
    //private final WebClient.Builder webClientBuilder;

  // actor actor =new actor();
    @Autowired
    EmailControllers EC;



    private final WebClient.Builder webClientBuilder;
    @GetMapping
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    List<Reclamation> retrieveAll(){


        List<Reclamation> rec= reclamationService.retrieveAll();
        List<String> interdit = Arrays.asList("mot1", "mot2", "mot3");
        for (Reclamation reclamation:rec){


            String censoredreclamation  = reclamation.getDescription();
            for (String motInterdit : interdit) {
                String ss=  censoredreclamation.replaceAll(motInterdit, "***");
                reclamation.setDescription(ss);



            }
        }
        return rec;
    }
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    ResponseEntity<String> add(@RequestBody Reclamation r){
        String message;



        /*ResponseEntity<actor> responseEntity = webClientBuilder.build().get()
                .uri("http://auth-service/api/auth/getuser")
                .retrieve()
                .toEntity(actor.class).block();
        actor actor = responseEntity.getBody();*/

        // r.setIduser(7);
       // r.setIduser(5);
         r.setStatut(false);
         //r.setId(8);
         System.out.println(r.getIduser()+r.getSubject()+r.getDescription()+r.isStatut());
          reclamationService.add(r);
//04030232

       // EC.ApplicationMail();

        //System.out.println(r.getId()+r.getIduser()+r.getSubject()+r.getDescription()+r.isStatut());


                message="votre reclamation a été envoyée";
                return ResponseEntity.ok(message);



    }
    @PutMapping("/edit/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    Reclamation update(@RequestBody Reclamation r,@PathVariable("id") Integer id){

        return reclamationService.updateReclamation(id, r);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    void remove(@PathVariable("id") Integer id){

        reclamationService.remove(id);
    }
    @GetMapping("/{id}")
    @ResponseBody
    Reclamation retrieve(@PathVariable("id") Integer id){
        List<String> interdit = Arrays.asList("mot1", "mot2", "mot3");
        Reclamation reclamation= reclamationService.retrieve(id);
        String censoredreclamation  = reclamation.getDescription();
        for (String motInterdit : interdit) {
           String ss=  censoredreclamation.replaceAll("mot3", "***");
            reclamation.setDescription(ss);



        }

        return reclamation;




    }
    @GetMapping("/getReclamationByUser/{iduser}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Reclamation> getReclamationByUser (@PathVariable("iduser") Long iduser)
    {

        List<Reclamation> rec=reclamationService.getReclamationByUser(iduser);
        List<String> interdit = Arrays.asList("mot1", "mot2", "mot3");
        for (Reclamation reclamation:rec){


            String censoredreclamation  = reclamation.getDescription();
            for (String motInterdit : interdit) {
                String ss=  censoredreclamation.replaceAll(motInterdit, "***");
                reclamation.setDescription(ss);



            }
        }
        return rec;
    }


}
