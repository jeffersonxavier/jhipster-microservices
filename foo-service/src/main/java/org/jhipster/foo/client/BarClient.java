package org.jhipster.foo.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.foo.service.dto.BarDTO;
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
}
