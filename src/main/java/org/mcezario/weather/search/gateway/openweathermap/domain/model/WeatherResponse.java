package org.mcezario.weather.search.gateway.openweathermap.domain.model;

import java.util.List;

public final class WeatherResponse {

	private String name;
	private List<WeatherDescription> weather;
	private Main main;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WeatherDescription> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherDescription> weather) {
		this.weather = weather;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
