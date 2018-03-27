package org.jhipster.gateway.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.parebem.clientProvider.MicroserviceClient;

@Component
public class GeneralClient extends MicroserviceClient {

    public GeneralClient() {
        super("");
    }

    public String randomRequest() {
        try {
            ResponseEntity<String> response = doRequest(null, "https://gturnquist-quoters.cfapps.io/api/", "random", HttpMethod.GET, null, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "";
        }
    }
}
