package com.weather.model.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CityWeatherForecastInfo {
	@JsonProperty("city")
	private City city;
	@JsonProperty("list")
	private List<DateWiseForecastInfo> list;

	public CityWeatherForecastInfo() {
		this.list = new ArrayList<>();
	}


	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<DateWiseForecastInfo> getList() {
		return list;
	}

	public void setList(List<DateWiseForecastInfo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "CityWeatherForecastInfo{" +
				"city=" + city +
				", list=" + list +
				'}';
	}
}
