package aha.oremta.jp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author aha-oretama
 * @Date 2016/12/11
 */
@Service
public class DoubleCalculateService {

    private final RestTemplate restTemplate;
    private static final String PORT = "8080";

    @Autowired
    public DoubleCalculateService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public int getDoubleDivision(int divisor, int dividend) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl("http://localhost:" + PORT + "/api/v1/calculate/division")
                .queryParam("divisor", divisor)
                .queryParam("dividend", dividend);

        ResponseEntity<Map> response1 =
                restTemplate.exchange(builder1.build().encode().toUri(), HttpMethod.GET,
                        new HttpEntity<>(headers),
                        Map.class);

        UriComponentsBuilder builder2 = UriComponentsBuilder.fromHttpUrl("http://localhost:" + PORT + "/api/v1/calculate/division")
                .queryParam("divisor", response1.getBody().get("quotient"))
                .queryParam("dividend", dividend);

        ResponseEntity<Map> response2 =
                restTemplate.exchange(builder2.build().encode().toUri(), HttpMethod.GET,
                        new HttpEntity<>(headers),
                        Map.class);

        return Integer.valueOf(response2.getBody().get("quotient").toString());
    }
}
