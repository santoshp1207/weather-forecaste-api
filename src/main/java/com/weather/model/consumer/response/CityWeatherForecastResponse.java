package com.weather.model.consumer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.model.consumer.City;

import java.util.ArrayList;
import java.util.List;

public class CityWeatherForecastResponse {
	@JsonProperty("city")
	private String city;
	@JsonProperty("dateWiseForecast")
	private List<DateWiseMinMaxTemp> dateWiseForecast;

	public CityWeatherForecastResponse() {
		this.dateWiseForecast = new ArrayList<>();
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<DateWiseMinMaxTemp> getDateWiseForecast() {
		return dateWiseForecast;
	}

	public void setDateWiseForecast(List<DateWiseMinMaxTemp> dateWiseForecast) {
		this.dateWiseForecast = dateWiseForecast;
	}
}
