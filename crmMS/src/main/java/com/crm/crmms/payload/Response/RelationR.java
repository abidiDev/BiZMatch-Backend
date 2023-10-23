package com.crm.crmms.payload.Response;

import com.crm.crmms.entities.Estatus;
import com.crm.crmms.models.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.ws.soap.Addressing;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RelationR implements Serializable {
    Actor actor;
    Estatus estatus;
    private Integer id;

}
