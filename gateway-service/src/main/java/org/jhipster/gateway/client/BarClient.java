package org.jhipster.gateway.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.gateway.service.dto.BarDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class BarClient extends AbstractMicroserviceClient<BarDTO> {

    public BarClient() {
        super("bar");
    }

    public List<BarDTO> findAll() {
        return Arrays.asList(restTemplate.exchange(getUrl("bars"), HttpMethod.GET, getJsonEntity(null), BarDTO[].class).getBody());
    }

    public BarDTO getOne(Long id) {
        return restTemplate.exchange(getUrl("bars", 1), HttpMethod.GET, getJsonEntity(null), BarDTO.class).getBody();
    }

    public BarDTO create(BarDTO bar) {
        return restTemplate.exchange(getUrl("bars"), HttpMethod.POST, getJsonEntity(bar), BarDTO.class).getBody();
    }

    public BarDTO update(BarDTO bar) {
        return restTemplate.exchange(getUrl("bars"), HttpMethod.PUT, getJsonEntity(bar), BarDTO.class).getBody();
    }

    public void delete(Long id) {
        restTemplate.exchange(getUrl("bars", id), HttpMethod.DELETE, getJsonEntity(null), BarDTO.class);
    }
}
