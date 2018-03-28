package org.jhipster.gateway.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.parebem.clientProvider.GeneralClient;

@Component
public class RandomClient extends GeneralClient {

    public String randomRequest() {
        try {
            ResponseEntity<String> response = doRequest(null, "https://gturnquist-quoters.cfapps.io/api/", "random", HttpMethod.GET, null, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "";
        }
    }
}
