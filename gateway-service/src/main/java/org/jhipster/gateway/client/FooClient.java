package org.jhipster.gateway.client;

import org.jhipster.gateway.service.dto.FooDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FooClient extends AbstractMicroserviceClient<FooDTO> {

    public FooClient() {
        super("foo");
    }

    public FooDTO getOne(Long id) {
        try {
            ResponseEntity<FooDTO> response = doRequest("foos", HttpMethod.GET, null, FooDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
