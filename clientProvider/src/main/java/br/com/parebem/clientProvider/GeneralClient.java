package br.com.parebem.clientProvider;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public class GeneralClient extends AbstractClient {

    protected static<E> ResponseEntity<E> doRequest(String authHeader, String baseUrl, String path,
            HttpMethod method, Object entity, Class<E> clazz) throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(baseUrl + path, method, buildJsonEntity(authHeader, entity), clazz);
    }

    protected static<E> ResponseEntity<E[]> doRequestToArray(String authHeader, String baseUrl, String path,
            HttpMethod method, Object entity, Class<E[]> clazz) throws JsonProcessingException, RestClientException {
        return restTemplate.exchange(baseUrl + path, method, buildJsonEntity(authHeader, entity), clazz);
    }
}
