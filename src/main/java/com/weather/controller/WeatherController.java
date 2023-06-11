package com.weather.controller;

import com.weather.model.consumer.response.CityWeatherForecastResponse;
import com.weather.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class WeatherController {
	@Autowired
	WeatherService service;

	@ApiOperation(value = "This will give you Weather Information for any given city", nickname = "getWeatherInfo", notes = "Query list of products", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful performs the operation", response = ResponseEntity.class),
			@ApiResponse(code = 400, message = "Malformed syntax."),
			@ApiResponse(code = 404, message = "Data not available"),
			@ApiResponse(code = 500, message = "Internal error") })
	@GetMapping("/{city}")
	public ResponseEntity<CityWeatherForecastResponse> getWeatherInfo(@PathVariable("city") String city, @RequestParam(value = "offlineMode", required = false) String offlineMode) {
		return new ResponseEntity(service.getWeatherForCity(city, offlineMode), HttpStatus.OK);
	}
}
