package reclamation.reclamations.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class actor {

    private Long id;
    private String FullName;

    private Date Birthdate;
    private  int phone;
    private String gender;
    private String email;
    private String username;

    private String password;
    private String picture;
    private String address;



}
