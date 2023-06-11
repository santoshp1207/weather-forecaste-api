package com.weather.model.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainInfo {
    @JsonProperty("temp")
    double temp;
    @JsonProperty("temp_min")
    double temp_min;
    @JsonProperty("temp_max")
    double temp_max;
    @JsonProperty("pressure")
    double pressure;
    @JsonProperty("humidity")
    double humidity;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
