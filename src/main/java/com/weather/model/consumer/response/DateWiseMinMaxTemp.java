package com.weather.model.consumer.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class DateWiseMinMaxTemp {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@JsonProperty("minTemp")
	private double minTemp;
	@JsonProperty("maxTemp")
	private double maxTemp;
	@JsonProperty("weatherAlert")
	private String weatherAlert;
	@JsonProperty("minWindSpeed")
	private double minWindSpeed;
	@JsonProperty("maxWindSpeed")
	private double maxWindSpeed;
	@JsonProperty("windSpeedAction")
	private String windSpeedAction;

	@JsonProperty("thunderstorms")
	private boolean thunderstorms;

	@JsonProperty("thunderstormsAction")
	private String thunderstormsAction;

	private int countOfDate;

	public DateWiseMinMaxTemp() {}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}

	public double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	public double getMinWindSpeed() {
		return minWindSpeed;
	}

	public void setMinWindSpeed(double minWindSpeed) {
		this.minWindSpeed = minWindSpeed;
	}

	public double getMaxWindSpeed() {
		return maxWindSpeed;
	}

	public void setMaxWindSpeed(double maxWindSpeed) {
		this.maxWindSpeed = maxWindSpeed;
	}

	public int getCountOfDate() {
		return countOfDate;
	}

	public void setCountOfDate(int countOfDate) {
		this.countOfDate = countOfDate;
	}

	public String getWeatherAlert() {
		return weatherAlert;
	}

	public void setWeatherAlert(String weatherAlert) {
		this.weatherAlert = weatherAlert;
	}

	public String getWindSpeedAction() {
		return windSpeedAction;
	}

	public void setWindSpeedAction(String windSpeedAction) {
		this.windSpeedAction = windSpeedAction;
	}

	public boolean isThunderstorms() {
		return thunderstorms;
	}

	public void setThunderstorms(boolean thunderstorms) {
		this.thunderstorms = thunderstorms;
	}

	public String getThunderstormsAction() {
		return thunderstormsAction;
	}

	public void setThunderstormsAction(String thunderstormsAction) {
		this.thunderstormsAction = thunderstormsAction;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DateWiseMinMaxTemp that = (DateWiseMinMaxTemp) o;
		return Double.compare(that.minTemp, minTemp) == 0 && Double.compare(that.maxTemp, maxTemp) == 0 && Double.compare(that.minWindSpeed, minWindSpeed) == 0 && Double.compare(that.maxWindSpeed, maxWindSpeed) == 0 && thunderstorms == that.thunderstorms && countOfDate == that.countOfDate && Objects.equals(date, that.date) && Objects.equals(weatherAlert, that.weatherAlert) && Objects.equals(windSpeedAction, that.windSpeedAction) && Objects.equals(thunderstormsAction, that.thunderstormsAction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, minTemp, maxTemp, weatherAlert, minWindSpeed, maxWindSpeed, windSpeedAction, thunderstorms, thunderstormsAction, countOfDate);
	}

	@Override
	public String toString() {
		return "DateWiseMinMaxTemp{" +
				"date=" + date +
				", minTemp=" + minTemp +
				", maxTemp=" + maxTemp +
				", weatherAction='" + weatherAlert + '\'' +
				", minWindSpeed=" + minWindSpeed +
				", maxWindSpeed=" + maxWindSpeed +
				", windSpeedAction='" + windSpeedAction + '\'' +
				", thunderstorms=" + thunderstorms +
				", thunderstormsAction='" + thunderstormsAction + '\'' +
				", countOfDate=" + countOfDate +
				'}';
	}
}
