package com.weather.controller;

import com.weather.exceptions.WeatherDataNotFoundException;
import com.weather.exceptions.WeatherDataParseException;
import com.weather.exceptions.WeatherError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class WeatherExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<WeatherError> handleException(WeatherDataParseException exc){
        WeatherError error = new WeatherError();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage(exc.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<WeatherError> handleException(RestClientException exc){
        WeatherError error = new WeatherError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(exc.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<WeatherError> handleException(WeatherDataNotFoundException exc){
        WeatherError error = new WeatherError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(exc.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
