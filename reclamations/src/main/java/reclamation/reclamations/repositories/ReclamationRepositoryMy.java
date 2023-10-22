package reclamation.reclamations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reclamation.reclamations.persistence.Reclamation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepositoryMy  extends JpaRepository<Reclamation, Integer> {
    @Query("SELECT c FROM Reclamation c where c.iduser= :iduser ")
    List<Reclamation> getReclamationByUser(@Param("iduser") Long iduser);



}
