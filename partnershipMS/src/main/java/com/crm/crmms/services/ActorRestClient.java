package com.crm.crmms.services;

import com.crm.crmms.models.Actor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="AUTH-SERVICE")
public interface ActorRestClient {
    @GetMapping(path = "/api/auth/actorById/{id}")
    Actor findActorById(@PathVariable Long id);
}
