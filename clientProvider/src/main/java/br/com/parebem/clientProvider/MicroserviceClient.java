package br.com.parebem.clientProvider;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class MicroserviceClient {

    private static String serviceName;
    
    private static ObjectMapper staticMapper;
    
    @Autowired
    private ObjectMapper mapper;
	
	public MicroserviceClient(String serviceName) {
		MicroserviceClient.serviceName = serviceName;
    }
	
    private static LoadBalancerClient loadBalancerClient;
    private static RestTemplate restTemplate;
	
	@Autowired(required = false)
    private void setRestTemplate(RestTemplateBuilder builder) {
        MicroserviceClient.restTemplate = builder.build();
    }
	
	@Autowired(required = false)
    private void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
        MicroserviceClient.loadBalancerClient = loadBalancerClient;
    }
	
	@PostConstruct
	private void init() {
		MicroserviceClient.staticMapper = mapper;
	}
	
	protected static<E> ResponseEntity<E> doRequest(String authHeader, String path, HttpMethod method, Object entity, Class<E> clazz)
			throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(getUrl(path), method, buildJsonEntity(authHeader, entity), clazz);
    }

    protected static<E> ResponseEntity<E[]> doRequestToArray(String authHeader, String path, HttpMethod method, Object entity, Class<E[]> clazz)
    		throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(getUrl(path), method, buildJsonEntity(authHeader, entity), clazz);
    }
    
    protected static<E> ResponseEntity<E> doRequest(String authHeader, String baseUrl, String path, HttpMethod method, Object entity, Class<E> clazz)
			throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(baseUrl + path, method, buildJsonEntity(authHeader, entity), clazz);
    }

    protected static<E> ResponseEntity<E[]> doRequestToArray(String authHeader, String baseUrl, String path, HttpMethod method, Object entity, Class<E[]> clazz)
    		throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(baseUrl + path, method, buildJsonEntity(authHeader, entity), clazz);
    }

    private static String getUrl(String path) {
        ServiceInstance instance = loadBalancerClient.choose(serviceName);
        
        String prefix = instance.isSecure() ? "https://" : "http://";
        String url = prefix + instance.getHost() + ":" + instance.getPort() + "/api/" + path;
        System.out.println("request in " + url);

        return url;
    }

    private static HttpEntity<String> buildJsonEntity(String authHeader, Object entity) throws JsonProcessingException {
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
