package org.mcezario.weather.search.gateway.application.domain.model;

import java.util.List;

public class WeatherResponse {

	private String name;
	private List<Weather> weather;
	private Main main;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
