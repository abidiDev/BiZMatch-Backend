package reclamation.reclamations.services.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import reclamation.reclamations.persistence.Reclamation;

import java.util.List;

public interface ReclamationService  {
   public Reclamation updateReclamation(Integer id, Reclamation reclamation);
   //public List<Reclamation> getReclamationByUser(String name);
   List<Reclamation> retrieveAll();
   void add(Reclamation reclamation);
   void update(Reclamation reclamation);
   void remove(Integer id);
   Reclamation retrieve(Integer id);
    List<Reclamation> getReclamationByUser(Long iduser);
}
