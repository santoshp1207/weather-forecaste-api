package com.weather.builder;

import com.weather.common.CommonData;
import com.weather.model.consumer.CityWeatherForecastInfo;
import com.weather.model.consumer.DateWiseForecastInfo;
import com.weather.model.consumer.MainInfo;
import com.weather.model.consumer.Weather;
import com.weather.model.consumer.Wind;
import com.weather.model.consumer.response.CityWeatherForecastResponse;
import com.weather.service.WeatherServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CityWeatherForecastResponseBuilderTest {
    @InjectMocks
    private CityWeatherForecastResponseBuilder responseBuilder;

    @Test
    public void getBuildForecastResponse() {
        String city = "delhi";
        MainInfo mainInfo = CommonData.getMainInfo(10.0, 40.4);
        Weather weather = CommonData.getWeather("Use sunscreen lotion");
        Wind wind = CommonData.getWind(20);
        DateWiseForecastInfo dateWiseForecastInfo = CommonData.getDateWiseForecastInfo(new Date(), mainInfo, weather, wind);
        List<DateWiseForecastInfo> list = new ArrayList<>();
        list.add(dateWiseForecastInfo);

        CityWeatherForecastInfo cityWeatherForecastInfo = CommonData.getCityWeatherForecastInfo(city, list);

        CityWeatherForecastResponse forecastResponse = responseBuilder.buildForecastResponse(cityWeatherForecastInfo);
        Assert.assertNotNull(forecastResponse);
    }
}
