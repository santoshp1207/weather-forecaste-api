package com.weather.exceptions;

public class WeatherDataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WeatherDataNotFoundException(String message){
		super(message);
	}
}
