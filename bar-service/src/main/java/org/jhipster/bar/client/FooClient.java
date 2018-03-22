package org.jhipster.bar.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.bar.service.dto.FooDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.parebem.clientProvider.MicroserviceClient;

@Component
public class FooClient extends MicroserviceClient<FooDTO> {

    public FooClient() {
        super("foo");
    }

    public List<FooDTO> findAll() {
        String authorization = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        
        try {
            ResponseEntity<FooDTO[]> response = doRequestToArray(authorization, "foos", HttpMethod.GET, null, FooDTO[].class);
            return Arrays.asList(response.getBody());    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
