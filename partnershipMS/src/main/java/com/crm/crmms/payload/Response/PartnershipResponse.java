package com.crm.crmms.payload.Response;

import com.crm.crmms.entities.EPartnershipType;
import com.crm.crmms.entities.EStatus;
import com.crm.crmms.entities.Entreprise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PartnershipResponse implements Serializable {
    Entreprise entreprise;
    EPartnershipType type;
    String Pindicator;
    @Enumerated(EnumType.STRING)

    EStatus status;


}
