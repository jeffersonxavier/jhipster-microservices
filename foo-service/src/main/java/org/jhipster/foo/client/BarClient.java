package org.jhipster.foo.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.foo.service.dto.BarDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BarClient extends AbstractMicroserviceClient<BarDTO> {

    public BarClient() {
        super("bar");
    }

    public List<BarDTO> findAll() {
        try {
            ResponseEntity<BarDTO[]> response = doRequestToArray("bars", HttpMethod.GET, null, BarDTO[].class);
            return Arrays.asList(response.getBody());    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
