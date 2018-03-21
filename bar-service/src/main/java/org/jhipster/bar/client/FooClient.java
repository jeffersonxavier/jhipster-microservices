package org.jhipster.bar.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.bar.service.dto.FooDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class FooClient extends AbstractMicroserviceClient<FooDTO> {

    public FooClient() {
        super("foo");
    }

    public List<FooDTO> findAll() {
        return Arrays.asList(restTemplate.exchange(getUrl("foos"), HttpMethod.GET, getJsonEntity(null), FooDTO[].class).getBody());
    }
}
