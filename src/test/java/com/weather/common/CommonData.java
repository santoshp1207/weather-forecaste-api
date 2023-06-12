package com.weather.common;

import com.weather.model.consumer.City;
import com.weather.model.consumer.CityWeatherForecastInfo;
import com.weather.model.consumer.DateWiseForecastInfo;
import com.weather.model.consumer.MainInfo;
import com.weather.model.consumer.Weather;
import com.weather.model.consumer.Wind;
import com.weather.model.consumer.response.CityWeatherForecastResponse;
import com.weather.model.consumer.response.DateWiseMinMaxTemp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonData {
    public static CityWeatherForecastInfo getCityWeatherForecastInfo(String cityName, List<DateWiseForecastInfo> list) {
        CityWeatherForecastInfo cityWeatherForecastInfo = new CityWeatherForecastInfo();
        City city = new City();
        city.setName(cityName);
        cityWeatherForecastInfo.setCity(city);
        cityWeatherForecastInfo.setList(list);
        return cityWeatherForecastInfo;

    }

    public static DateWiseForecastInfo getDateWiseForecastInfo(Date dt_txt, MainInfo mainInfo, Weather weather, Wind wind) {
        DateWiseForecastInfo dateWiseForecastInfo = new DateWiseForecastInfo();
        dateWiseForecastInfo.setDt_txt(dt_txt);
        dateWiseForecastInfo.setMain(mainInfo);
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        dateWiseForecastInfo.setWeather(weatherList);
        dateWiseForecastInfo.setWind(wind);

        return dateWiseForecastInfo;
    }

    public static MainInfo getMainInfo(double temp_min, double temp_max) {
        MainInfo mainInfo = new MainInfo();
        mainInfo.setTemp_min(temp_min);
        mainInfo.setTemp_max(temp_max);
        return mainInfo;
    }

    public static Weather getWeather(String description) {
        Weather weather = new Weather();
        weather.setDescription(description);
        return weather;
    }

    public static Wind getWind(double speed) {
        Wind wind = new Wind();
        wind.setSpeed(speed);
        return wind;
    }

    public static DateWiseMinMaxTemp getDateWiseMinMaxTemp(LocalDate date, double minTemp, double maxTemp, String weatherAlert,
                                                     double minWindSpeed, double maxWindSpeed, String windSpeedAction, boolean thunderstorms, String thunderstormsAction) {
        DateWiseMinMaxTemp dateWiseMinMaxTemp = new DateWiseMinMaxTemp();
        dateWiseMinMaxTemp.setDate(date);
        dateWiseMinMaxTemp.setMinTemp(minTemp);
        dateWiseMinMaxTemp.setMaxTemp(maxTemp);
        dateWiseMinMaxTemp.setWeatherAlert(weatherAlert);
        dateWiseMinMaxTemp.setMinWindSpeed(minWindSpeed);
        dateWiseMinMaxTemp.setMaxWindSpeed(maxWindSpeed);
        dateWiseMinMaxTemp.setWindSpeedAction(windSpeedAction);
        dateWiseMinMaxTemp.setThunderstorms(thunderstorms);
        dateWiseMinMaxTemp.setThunderstormsAction(thunderstormsAction);
        return dateWiseMinMaxTemp;
    }

    public static CityWeatherForecastResponse getCityWeatherForecastResponse(String city, List<DateWiseMinMaxTemp> dateWiseForecast) {
        CityWeatherForecastResponse cityWeatherForecastResponse = new CityWeatherForecastResponse();
        cityWeatherForecastResponse.setDateWiseForecast(dateWiseForecast);
        return cityWeatherForecastResponse;
    }
}
