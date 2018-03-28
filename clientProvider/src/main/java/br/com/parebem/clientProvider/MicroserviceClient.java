package br.com.parebem.clientProvider;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class MicroserviceClient extends AbstractClient {

    private static String serviceName;
    
	public MicroserviceClient(String serviceName) {
		MicroserviceClient.serviceName = serviceName;
    }
	
	protected static<E> ResponseEntity<E> doRequest(String authHeader, String path, HttpMethod method, Object entity, Class<E> clazz)
			throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(getUrl(path), method, buildJsonEntity(authHeader, entity), clazz);
    }

    protected static<E> ResponseEntity<E[]> doRequestToArray(String authHeader, String path, HttpMethod method, Object entity, Class<E[]> clazz)
    		throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(getUrl(path), method, buildJsonEntity(authHeader, entity), clazz);
    }
    
    private static String getUrl(String path) {
        ServiceInstance instance = loadBalancerClient.choose(serviceName);
        
        String prefix = instance.isSecure() ? "https://" : "http://";
        String url = prefix + instance.getHost() + ":" + instance.getPort() + "/api/" + path;
        System.out.println("request in " + url);

        return url;
    }
}
