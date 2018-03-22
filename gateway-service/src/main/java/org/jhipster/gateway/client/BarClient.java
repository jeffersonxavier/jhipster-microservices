package org.jhipster.gateway.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.gateway.service.dto.BarDTO;
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

    public BarDTO getOne(Long id) {
        try {
            ResponseEntity<BarDTO> response = doRequest("bars/" + id, HttpMethod.GET, null, BarDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BarDTO create(BarDTO bar) {
        try {
            ResponseEntity<BarDTO> response = doRequest("bars", HttpMethod.POST, bar, BarDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BarDTO update(BarDTO bar) {
        try {
            ResponseEntity<BarDTO> response = doRequest("bars", HttpMethod.PUT, bar, BarDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Long id) {
        try {
            doRequest("bars/" + id, HttpMethod.DELETE, null, BarDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
