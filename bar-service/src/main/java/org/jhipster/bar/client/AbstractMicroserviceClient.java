package org.jhipster.bar.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractMicroserviceClient<T> {

    private String serviceName;

    public AbstractMicroserviceClient(String serviceName) {
        this.serviceName = serviceName;
    }

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private LoadBalancerClient loadBalancerClient;

    @Autowired(required = false)
        public void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
            this.loadBalancerClient = loadBalancerClient;
    }

    protected String getUrl(String path) {
        ServiceInstance instance = this.loadBalancerClient.choose(this.serviceName);
        
        String prefix = instance.isSecure() ? "https://" : "http://";
        String url = prefix + instance.getHost() + ":" + instance.getPort() + "/api/" + path;
        System.out.println("request in " + url);

        return url;
    }

    protected String getUrl(String path, long id) {
        return getUrl(path + "/" + id);
    }

    protected HttpEntity<String> getJsonEntity(T entity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials());
        
        HttpEntity<String> result = new HttpEntity<>(headers);
    
        if (entity != null) {
            try {
                String entityJson = mapper.writeValueAsString(entity);
                result = new HttpEntity<>(entityJson, headers); 
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
        }
    
        return result;
    }
}
