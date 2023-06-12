package com.weather.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.builder.CityWeatherForecastResponseBuilder;
import com.weather.common.CommonData;
import com.weather.exceptions.WeatherDataNotFoundException;
import com.weather.model.consumer.City;
import com.weather.model.consumer.CityWeatherForecastInfo;
import com.weather.model.consumer.DateWiseForecastInfo;
import com.weather.model.consumer.MainInfo;
import com.weather.model.consumer.Weather;
import com.weather.model.consumer.Wind;
import com.weather.model.consumer.response.CityWeatherForecastResponse;
import com.weather.model.consumer.response.DateWiseMinMaxTemp;
import com.weather.webclients.RestWebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherServiceImplTest {

    @InjectMocks
    private WeatherServiceImpl weatherService;
    @Mock
    private CityWeatherForecastResponseBuilder responseBuilder;
    @Mock
    private RestWebClient restClient;


    @Test
    public void getWeatherForCityForSuccessful() {
        String city = "delhi";
        MainInfo mainInfo = CommonData.getMainInfo(10.0, 40.4);
        Weather weather = CommonData.getWeather("Use sunscreen lotion");
        Wind wind = CommonData.getWind(20);
        DateWiseForecastInfo dateWiseForecastInfo = CommonData.getDateWiseForecastInfo(new Date(), mainInfo, weather, wind);
        List<DateWiseForecastInfo> list = new ArrayList<>();
        list.add(dateWiseForecastInfo);

        CityWeatherForecastInfo cityWeatherForecastInfo = CommonData.getCityWeatherForecastInfo(city, list);

        Mockito.when(restClient.getCityWeatherForecastData(city, "false")).thenReturn(cityWeatherForecastInfo);

        DateWiseMinMaxTemp dateWiseMinMaxTemp = CommonData.getDateWiseMinMaxTemp(LocalDate.of(2023, 1, 8),
        10.9, 40.4,"Use sunscreen lotion",10.8,22.8, "", false, "");
        List<DateWiseMinMaxTemp> dateWiseForecastList = new ArrayList<>();
        dateWiseForecastList.add(dateWiseMinMaxTemp);
        CityWeatherForecastResponse cityWeatherForecastResponse = CommonData.getCityWeatherForecastResponse(city, dateWiseForecastList);
        Mockito.when(responseBuilder.buildForecastResponse(cityWeatherForecastInfo)).thenReturn(cityWeatherForecastResponse);

        CityWeatherForecastResponse forecastResponse = weatherService.getWeatherForCity(city, "false");
        Assert.assertNotNull(forecastResponse);
    }

    @Test(expected = WeatherDataNotFoundException.class)
    public void getWeatherForCityForFail() {
        String city = "dubai";
        Mockito.when(restClient.getCityWeatherForecastData(city, "false")).thenReturn(null);
        CityWeatherForecastResponse forecastResponse = weatherService.getWeatherForCity(city, "false");
        Assert.assertNotNull(forecastResponse);
    }
}
