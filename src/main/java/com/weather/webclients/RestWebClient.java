package com.weather.webclients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.weather.model.consumer.CityWeatherForecastInfo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
@Service
public class RestWebClient {
	static final String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=";
	static final String appId = "&appid=d2929e9483efc82c82c32ee7e02d563e";
	static final String responseType = "&mode=json&units=metric"; //mode
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment environment;
	private Map<String, CityWeatherForecastInfo> localCache = new HashMap<>();

	public RestWebClient() {}
	@HystrixCommand(fallbackMethod="getCachedWeather")
	public CityWeatherForecastInfo getCityWeatherForecastData(String city, String offlineMode) throws RestClientException {
		CityWeatherForecastInfo response = null;
//		String mode = environment.getProperty("app.offline.mode");
		if(offlineMode != null && "true".equalsIgnoreCase(offlineMode)) {
			response = localCache.get(city);
		}
		else {
			String reqUrl = new StringBuilder(apiUrl).append(city).append(responseType).append(appId).toString();
			response = restTemplate.getForObject(reqUrl, CityWeatherForecastInfo.class);
			localCache.put(city, response);
		}
		return response;
	}

	public CityWeatherForecastInfo getCachedWeather(String city, String offlineMode) {
		return localCache.get(city);
	}

}
