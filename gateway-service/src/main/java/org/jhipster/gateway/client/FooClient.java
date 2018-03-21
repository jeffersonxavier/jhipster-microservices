package org.jhipster.gateway.client;

import org.jhipster.gateway.service.dto.FooDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class FooClient extends AbstractMicroserviceClient<FooDTO> {

    public FooClient() {
        super("foo");
    }

    public FooDTO getOne(Long id) {
        return restTemplate.exchange(getUrl("foos", id), HttpMethod.GET, getJsonEntity(null), FooDTO.class).getBody();
    }
}
