package org.jhipster.gateway.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MicroserviceConfiguration {

    @Bean
    public RestTemplate buildRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
