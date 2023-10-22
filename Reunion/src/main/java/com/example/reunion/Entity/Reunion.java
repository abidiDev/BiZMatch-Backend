package com.example.reunion.Entity;


import lombok.*;
import org.hibernate.type.SerializableType;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reunion  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reunionId;
    @Temporal(TemporalType.DATE)
    private Date DateReunion;
    private String Location;
    private String agenda;
}
