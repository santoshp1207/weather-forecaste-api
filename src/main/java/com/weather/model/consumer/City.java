package com.weather.model.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    @JsonProperty("name")
    String name;
    @JsonProperty("country")
    String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
