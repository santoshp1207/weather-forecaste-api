package com.weather.builder;

import com.weather.model.consumer.CityWeatherForecastInfo;
import com.weather.model.consumer.response.CityWeatherForecastResponse;
import com.weather.model.consumer.response.DateWiseMinMaxTemp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CityWeatherForecastResponseBuilder {

    public CityWeatherForecastResponse buildForecastResponse(CityWeatherForecastInfo resDataFromApi) {
        Map<LocalDate, DateWiseMinMaxTemp>  forecastMapByDate = buildDateWiseMinMaxTempData(resDataFromApi);
        setWeatherAlerts(resDataFromApi, forecastMapByDate);

        CityWeatherForecastResponse forecastResponse = new CityWeatherForecastResponse();
        forecastResponse.setCity(resDataFromApi.getCity().getName());
        forecastResponse.setDateWiseForecast(new ArrayList<>(forecastMapByDate.values()));
        return forecastResponse;
    }

    private void setWeatherAlerts(CityWeatherForecastInfo resDataFromApi, Map<LocalDate, DateWiseMinMaxTemp> forecastMapByDate) {
        forecastMapByDate.values().forEach(dateWiseMinMaxTemp -> {
            if(dateWiseMinMaxTemp.getMaxTemp() > 40) {
                dateWiseMinMaxTemp.setWeatherAlert("Use sunscreen lotion");
            }
            else {
                resDataFromApi.getList().stream().forEach(dateWiseForecastInfo -> {
                    dateWiseForecastInfo.getWeather().stream().forEach(weather -> {
                        if(weather.getMain() != null && weather.getMain().toUpperCase().contains("Rain".toUpperCase())) {
                            dateWiseMinMaxTemp.setWeatherAlert("Carry umbrella");
                        }
                    });
                });
            }
            //wind and thunderstorms info here
            if(dateWiseMinMaxTemp.getMaxWindSpeed() > 10 && dateWiseMinMaxTemp.getMaxWindSpeed() <= 20) {
                dateWiseMinMaxTemp.setWindSpeedAction("It’s too windy, watch out!");
            }
            if(dateWiseMinMaxTemp.getMaxWindSpeed() > 20) {
                dateWiseMinMaxTemp.setThunderstormsAction("Don’t step out! A Storm is brewing!");
            }
        });
    }

    private Map<LocalDate, DateWiseMinMaxTemp> buildDateWiseMinMaxTempData(CityWeatherForecastInfo resDataFromApi) {
        Map<LocalDate, DateWiseMinMaxTemp>  forecastMapByDate = new TreeMap<>();

        resDataFromApi.getList().stream().forEach(dateWiseForecastInfo -> {
            if(dateWiseForecastInfo.getDt_txt().after(new Date())) {
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = outputFormat.format(dateWiseForecastInfo.getDt_txt());
                LocalDate forecastDate = LocalDate.parse(date);
                DateWiseMinMaxTemp dateWiseMinMaxTemp = forecastMapByDate.get(forecastDate);
                // setting min-max temp & wind info starts here
                if(dateWiseMinMaxTemp == null) {
                    dateWiseMinMaxTemp = new DateWiseMinMaxTemp();
                    dateWiseMinMaxTemp.setCountOfDate(1);
                    dateWiseMinMaxTemp.setDate(forecastDate);
                    dateWiseMinMaxTemp.setMinTemp(dateWiseForecastInfo.getMain().getTemp_min());
                    dateWiseMinMaxTemp.setMaxTemp(dateWiseForecastInfo.getMain().getTemp_max());
                    dateWiseMinMaxTemp.setMinWindSpeed(dateWiseForecastInfo.getWind().getSpeed());
                    dateWiseMinMaxTemp.setMaxWindSpeed(dateWiseForecastInfo.getWind().getSpeed());
                    forecastMapByDate.put(forecastDate, dateWiseMinMaxTemp);
                } else {
                    int count = dateWiseMinMaxTemp.getCountOfDate() + 1;
                    dateWiseMinMaxTemp.setCountOfDate(count);
                    if(dateWiseForecastInfo.getMain().getTemp_min() < dateWiseMinMaxTemp.getMinTemp()) {
                        dateWiseMinMaxTemp.setMinTemp(dateWiseForecastInfo.getMain().getTemp_min());
                    }
                    else if(dateWiseForecastInfo.getMain().getTemp_max() > dateWiseMinMaxTemp.getMaxTemp()) {
                        dateWiseMinMaxTemp.setMaxTemp(dateWiseForecastInfo.getMain().getTemp_max());
                    }

                    if(dateWiseForecastInfo.getWind().getSpeed() < dateWiseMinMaxTemp.getMinWindSpeed()) {
                        dateWiseMinMaxTemp.setMinWindSpeed(dateWiseForecastInfo.getWind().getSpeed());
                    }
                    else if(dateWiseForecastInfo.getWind().getSpeed() > dateWiseMinMaxTemp.getMaxWindSpeed()) {
                        dateWiseMinMaxTemp.setMaxWindSpeed(dateWiseForecastInfo.getWind().getSpeed());
                    }
                }
                // setting min-max temp & wind infoends here
            }

        });
        return forecastMapByDate.entrySet().stream().limit(3).sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }


}
