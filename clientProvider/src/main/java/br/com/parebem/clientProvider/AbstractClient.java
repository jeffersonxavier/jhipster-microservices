package br.com.parebem.clientProvider;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractClient {

    private static ObjectMapper staticMapper;
    
    @Autowired
    private ObjectMapper mapper;

    protected static LoadBalancerClient loadBalancerClient;
    protected static RestTemplate restTemplate;
	
	@Autowired(required = false)
    private void setRestTemplate(RestTemplateBuilder builder) {
        AbstractClient.restTemplate = builder.build();
    }
	
	@Autowired(required = false)
    private void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
        AbstractClient.loadBalancerClient = loadBalancerClient;
    }
	
	@PostConstruct
	private void init() {
		AbstractClient.staticMapper = mapper;
    }
    
    protected static HttpEntity<String> buildJsonEntity(String authHeader, Object entity) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        if (authHeader != null && !authHeader.isEmpty())
        	headers.set("Authorization", authHeader);

        HttpEntity<String> result = new HttpEntity<>(headers);
        
        if (entity != null) {
        	String entityJson = staticMapper.writeValueAsString(entity);
            result = new HttpEntity<>(entityJson, result.getHeaders());
        }

        return result;
    }
}
