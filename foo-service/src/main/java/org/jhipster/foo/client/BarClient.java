package org.jhipster.foo.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.foo.service.dto.BarDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.parebem.clientProvider.MicroserviceClient;

@Component
public class BarClient extends MicroserviceClient {

    public BarClient() {
        super("bar");
    }

    public List<BarDTO> findAll() {
        String authorization = "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        
        try {
            ResponseEntity<BarDTO[]> response = doRequestToArray(authorization, "bars", HttpMethod.GET, null, BarDTO[].class);
            return Arrays.asList(response.getBody());    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
