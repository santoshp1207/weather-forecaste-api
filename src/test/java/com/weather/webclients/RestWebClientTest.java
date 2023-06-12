package com.weather.webclients;

import com.weather.common.CommonData;
import com.weather.model.consumer.CityWeatherForecastInfo;
import com.weather.model.consumer.DateWiseForecastInfo;
import com.weather.model.consumer.MainInfo;
import com.weather.model.consumer.Weather;
import com.weather.model.consumer.Wind;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestWebClientTest {
    @InjectMocks
    private RestWebClient restWebClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Environment environment;

    @Test
    public void getCityWeatherForecastDataFromAPI() {
        String city = "delhi";
        MainInfo mainInfo = CommonData.getMainInfo(10.0, 40.4);
        Weather weather = CommonData.getWeather("Use sunscreen lotion");
        Wind wind = CommonData.getWind(20);
        DateWiseForecastInfo dateWiseForecastInfo = CommonData.getDateWiseForecastInfo(new Date(), mainInfo, weather, wind);
        List<DateWiseForecastInfo> list = new ArrayList<>();
        list.add(dateWiseForecastInfo);

        CityWeatherForecastInfo cityWeatherForecastInfo = CommonData.getCityWeatherForecastInfo(city, list);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(cityWeatherForecastInfo);
        CityWeatherForecastInfo forecastResponse = restWebClient.getCityWeatherForecastData(city, "false");
        Assert.assertNotNull(forecastResponse);
    }

    @Test
    public void getCityWeatherForecastDataFromCache() {
        String city = "delhi";
        CityWeatherForecastInfo forecastResponse = restWebClient.getCityWeatherForecastData(city, "true");
        Assert.assertNull(forecastResponse);
    }
}
