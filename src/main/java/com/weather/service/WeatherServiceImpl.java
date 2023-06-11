package com.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.builder.CityWeatherForecastResponseBuilder;
import com.weather.exceptions.WeatherDataNotFoundException;
import com.weather.model.consumer.CityWeatherForecastInfo;
import com.weather.model.consumer.response.CityWeatherForecastResponse;
import com.weather.webclients.RestWebClient;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	private CityWeatherForecastResponseBuilder responseBuilder;
	@Autowired
	private RestWebClient restClient;

	public WeatherServiceImpl() {}
	
	@Override
	public CityWeatherForecastResponse getWeatherForCity(String city, String offlineMode) {
		CityWeatherForecastInfo resDataFromApi = this.restClient.getCityWeatherForecastData(city, offlineMode);
		if(resDataFromApi == null) {
			throw new WeatherDataNotFoundException("Weather Data Not Found for " + city);
		}
		CityWeatherForecastResponse forecastResponse = this.responseBuilder.buildForecastResponse(resDataFromApi);
		return forecastResponse;
	}

}
