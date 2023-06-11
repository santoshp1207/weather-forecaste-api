package com.weather.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("com.weather")
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@EnableWebMvc
public class WeatherForecastApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastApplication.class, args);
	}
}
