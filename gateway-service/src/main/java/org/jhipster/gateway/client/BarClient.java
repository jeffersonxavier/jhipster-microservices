package org.jhipster.gateway.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.gateway.domain.Bar;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class BarClient extends AbstractMicroserviceClient<Bar> {

    public BarClient() {
        super("bar");
    }

    public List<Bar> findAll() {
        return Arrays.asList(restTemplate.exchange(getUrl("bars"), HttpMethod.GET, getJsonEntity(null), Bar[].class).getBody());
    }

    public Bar getOne(Long id) {
        return restTemplate.exchange(getUrl("bars", 1), HttpMethod.GET, getJsonEntity(null), Bar.class).getBody();
    }

    public Bar create(Bar bar) {
        return restTemplate.exchange(getUrl("bars"), HttpMethod.POST, getJsonEntity(bar), Bar.class).getBody();
    }

    public Bar update(Bar bar) {
        return restTemplate.exchange(getUrl("bars"), HttpMethod.PUT, getJsonEntity(bar), Bar.class).getBody();
    }

    public void delete(Long id) {
        restTemplate.exchange(getUrl("bars", id), HttpMethod.DELETE, getJsonEntity(null), Bar.class);
    }
}
