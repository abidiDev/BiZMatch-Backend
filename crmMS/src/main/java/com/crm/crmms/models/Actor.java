package com.crm.crmms.models;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class Actor {
    private Long id;
    private String FullName;

    private  int phone;
    private String email;


}
