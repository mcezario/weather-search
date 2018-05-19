package org.mcezario.weather.search.gateway.commons.domain.model;

import java.math.BigDecimal;
import java.util.Optional;

public final class Temperature {

	private final BigDecimal actual;
	private final Optional<BigDecimal> min;
	private final Optional<BigDecimal> max;

	private Temperature(final BigDecimal actual, final Optional<BigDecimal> min, final Optional<BigDecimal> max) {
		this.actual = actual;
		this.min = min;
		this.max = max;
	}

	public static Temperature fromClimaTempo(final int actualTemperature) {
		return new Temperature(new BigDecimal(actualTemperature), Optional.empty(), Optional.empty());
	}

	public static Temperature fromOpenWeatherMap(final double actual, final double min, final double max) {
		return new Temperature( //
				new BigDecimal(actual), //
				Optional.of(new BigDecimal(min)), //
				Optional.of(new BigDecimal(max)) //
		);
	}

	public BigDecimal actual() {
		return actual;
	}

	public Optional<BigDecimal> min() {
		return min;
	}

	public Optional<BigDecimal> max() {
		return max;
	}

}
