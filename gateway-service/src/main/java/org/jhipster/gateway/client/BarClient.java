package org.jhipster.gateway.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.gateway.service.dto.BarDTO;
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

    public BarDTO getOne(Long id) {
        String authorization = "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        try {
            ResponseEntity<BarDTO> response = doRequest(authorization, "bars/" + id, HttpMethod.GET, null, BarDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BarDTO create(BarDTO bar) {
        String authorization = "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        
        try {
            ResponseEntity<BarDTO> response = doRequest(authorization, "bars", HttpMethod.POST, bar, BarDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BarDTO update(BarDTO bar) {
        String authorization = "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        try {
            ResponseEntity<BarDTO> response = doRequest(authorization, "bars", HttpMethod.PUT, bar, BarDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Long id) {
        String authorization = "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        try {
            doRequest(authorization, "bars/" + id, HttpMethod.DELETE, null, BarDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
