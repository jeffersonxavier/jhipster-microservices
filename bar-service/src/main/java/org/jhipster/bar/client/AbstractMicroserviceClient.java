package org.jhipster.bar.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractMicroserviceClient<T> {

    private String serviceName;

    public AbstractMicroserviceClient(String serviceName) {
        this.serviceName = serviceName;
    }

    @Autowired
    private ObjectMapper mapper;

    private RestTemplate restTemplate;
    private LoadBalancerClient loadBalancerClient;

    @Autowired(required = false)
    public void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @Autowired(required = false)
    public void setRestTemplate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    protected ResponseEntity<T> doRequest(String path, HttpMethod method, Object entity, Class<T> clazz) throws RestClientException {
        return restTemplate.exchange(getUrl(path), method, buildJsonEntity(entity), clazz);
    }

    protected ResponseEntity<T[]> doRequestToArray(String path, HttpMethod method, Object entity, Class<T[]> clazz) throws RestClientException {
        return restTemplate.exchange(getUrl(path), method, buildJsonEntity(entity), clazz);
    }

    private String getUrl(String path) {
        ServiceInstance instance = this.loadBalancerClient.choose(this.serviceName);
        
        String prefix = instance.isSecure() ? "https://" : "http://";
        String url = prefix + instance.getHost() + ":" + instance.getPort() + "/api/" + path;
        System.out.println("request in " + url);

        return url;
    }

    private HttpEntity<String> buildJsonEntity(Object entity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials());

        HttpEntity<String> result = new HttpEntity<>(headers);

        try {
            String entityJson = mapper.writeValueAsString(entity);
            result = new HttpEntity<>(entityJson, result.getHeaders()); 
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }
}
