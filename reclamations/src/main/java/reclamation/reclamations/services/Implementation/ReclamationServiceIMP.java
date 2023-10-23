package reclamation.reclamations.services.Implementation;

import reclamation.reclamations.persistence.Reclamation;
import reclamation.reclamations.repositories.ReclamationRepositoryMy;
import reclamation.reclamations.services.Interface.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamationServiceIMP  implements ReclamationService {

    @Autowired
    private ReclamationRepositoryMy reclamationRepository;
    @Override
    public Reclamation updateReclamation(Integer id, Reclamation reclamation) {
        Reclamation existingReclamation = retrieve(id);
        existingReclamation.setDescription(reclamation.getDescription());
        existingReclamation.setStatut(reclamation.isStatut());
        existingReclamation.setSubject(reclamation.getSubject());
        existingReclamation.setIduser(reclamation.getIduser());



        return reclamationRepository.save(existingReclamation);
    }
    @Override
    public List<Reclamation> getReclamationByUser(Long iduser) {

        return 	reclamationRepository.getReclamationByUser( iduser);
    }


    @Override
    public List<Reclamation> retrieveAll() {
        try{
            return  reclamationRepository.findAll();
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }

    @Override
    public void add(Reclamation reclamation) {
        try{
            reclamationRepository.save(reclamation);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue add: " + err);
        }

    }

    @Override
    public void update(Reclamation reclamation) {
        try {
            System.out.println("mise a jour avec succes");
            reclamationRepository.save(reclamation);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }

    }

    @Override
    public void remove(Integer id) {
        try{
            Reclamation reclamation = reclamationRepository.findById(id).orElse(null);
            reclamationRepository.delete(reclamation);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
    }

    @Override
    public Reclamation retrieve(Integer id) {
        try{
            return  reclamationRepository.findById(id).get();
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }

}
