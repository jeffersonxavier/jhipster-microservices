package org.jhipster.gateway.client;

import java.util.Arrays;
import java.util.List;

import org.jhipster.gateway.domain.Foo;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class FooClient extends AbstractMicroserviceClient<Foo> {

    public FooClient() {
        super("foo");
    }

    public List<Foo> findAll() {
        return Arrays.asList(restTemplate.exchange(getUrl("foos"), HttpMethod.GET, getJsonEntity(null), Foo[].class).getBody());
    }

    public Foo create(Foo foo) {
        return restTemplate.exchange(getUrl("foos"), HttpMethod.POST, getJsonEntity(foo), Foo.class).getBody();
    }
}
