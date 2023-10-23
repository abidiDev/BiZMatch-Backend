package reclamation.reclamations.persistence;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class Reclamation implements Serializable {
    //  -------------------moslem-------------------

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject;
    private String description;
    private boolean statut;
    private long iduser;








}
