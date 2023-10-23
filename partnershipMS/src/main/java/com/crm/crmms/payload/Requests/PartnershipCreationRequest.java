package com.crm.crmms.payload.Requests;

import com.crm.crmms.entities.EPartnershipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PartnershipCreationRequest {
    private Integer idEntreprise1;

    private Integer idEntreprise2;
    private EPartnershipType partnershipType;
}
