package com.weather.model.consumer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.exceptions.WeatherDataParseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateWiseForecastInfo {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dt_txt;
	@JsonProperty("main")
	private MainInfo main;
	@JsonProperty("weather")
	private List<Weather> weather;
	@JsonProperty("wind")
	private Wind wind;
	@JsonProperty("visibility")
	private int visibility;

	public DateWiseForecastInfo() {}

	public Date getDt_txt() {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
//			Date date = inputFormat.parse(dt_txt);
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
//			return LocalDate.parse(dt_txt.toString());
			return dt_txt;
		} catch (Exception e) {
			throw new WeatherDataParseException(e.toString());
		}
	}

	public void setDt_txt(Date dt_txt) {
		this.dt_txt = dt_txt;
	}

	public MainInfo getMain() {
		return main;
	}

	public void setMain(MainInfo main) {
		this.main = main;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}



	@Override
	public String toString() {
		return "DateWiseForecastInfo{" +
				"dt_txt=" + dt_txt +
				", main=" + main +
				", weather=" + weather +
				", wind=" + wind +
				", visibility=" + visibility +
				'}';
	}
}
