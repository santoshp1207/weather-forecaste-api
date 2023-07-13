package com.weather.controller;

import com.weather.model.consumer.response.CityWeatherForecastResponse;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    public void getWeatherInfo() {
        String url = "http://localhost:"+ port + "/city/{city}";
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("city", "dubai");
        HttpEntity<String> entity = new HttpEntity<>(null, null);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        URI uri = uriComponentsBuilder.buildAndExpand(pathVariables).toUri();
        ResponseEntity<CityWeatherForecastResponse> response =   testRestTemplate.exchange(uri, HttpMethod.GET, entity, CityWeatherForecastResponse.class);
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody());
    }
}
