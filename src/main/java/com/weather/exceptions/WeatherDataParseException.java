package com.weather.exceptions;

public class WeatherDataParseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WeatherDataParseException(String message){
		super(message);
	}
}
