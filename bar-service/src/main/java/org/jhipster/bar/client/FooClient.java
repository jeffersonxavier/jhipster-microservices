package org.jhipster.bar.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.bar.service.dto.FooDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FooClient extends AbstractMicroserviceClient<FooDTO> {

    public FooClient() {
        super("foo");
    }

    public List<FooDTO> findAll() {
        try {
            ResponseEntity<FooDTO[]> response = doRequestToArray("foos", HttpMethod.GET, null, FooDTO[].class);
            return Arrays.asList(response.getBody());    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
