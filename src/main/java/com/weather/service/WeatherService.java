package com.weather.service;

import com.weather.model.consumer.CityWeatherForecastInfo;
import com.weather.model.consumer.response.CityWeatherForecastResponse;

public interface WeatherService {
	public CityWeatherForecastResponse getWeatherForCity(String city, String offlineMode);
}
