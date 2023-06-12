package com.weather.controller;

import com.weather.model.consumer.response.CityWeatherForecastResponse;
import com.weather.model.consumer.response.DateWiseMinMaxTemp;
import com.weather.service.WeatherService;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeatherController.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    WeatherService service;

    public void setUp() {
    }

    @Test
    public void testGetWeatherInfoForSuccess() throws Exception {

        CityWeatherForecastResponse forecastResponse = new CityWeatherForecastResponse();
        DateWiseMinMaxTemp dateWiseMinMaxTemp = new DateWiseMinMaxTemp();
        dateWiseMinMaxTemp.setDate(LocalDate.of(2023, 1, 8));
        dateWiseMinMaxTemp.setMaxTemp(50);
        dateWiseMinMaxTemp.setMaxTemp(50);
        dateWiseMinMaxTemp.setWeatherAlert("Use sunscreen lotion");

        Mockito.when(service.getWeatherForCity("delhi", "false")).thenReturn(forecastResponse);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/delhi")
                        .accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetWeatherInfoForFailure() throws Exception {

        CityWeatherForecastResponse forecastResponse = new CityWeatherForecastResponse();
        DateWiseMinMaxTemp dateWiseMinMaxTemp = new DateWiseMinMaxTemp();
        dateWiseMinMaxTemp.setDate(LocalDate.of(2023, 1, 8));
        dateWiseMinMaxTemp.setMaxTemp(50);
        dateWiseMinMaxTemp.setMaxTemp(50);
        dateWiseMinMaxTemp.setWeatherAlert("Use sunscreen lotion");

        Mockito.when(service.getWeatherForCity("delhi", "false")).thenReturn(forecastResponse);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/context-testing/delhi")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }
}
