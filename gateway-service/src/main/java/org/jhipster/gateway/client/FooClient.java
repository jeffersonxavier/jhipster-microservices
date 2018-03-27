package org.jhipster.gateway.client;

import org.jhipster.gateway.service.dto.FooDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.parebem.clientProvider.MicroserviceClient;

@Component
public class FooClient extends MicroserviceClient {

    public FooClient() {
        super("foo");
    }

    public FooDTO getOne(Long id) {
        String authorization = "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        try {
            ResponseEntity<FooDTO> response = doRequest(authorization, "foos/" + id, HttpMethod.GET, null, FooDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
