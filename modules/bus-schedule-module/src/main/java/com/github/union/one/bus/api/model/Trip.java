package com.github.union.one.bus.api.model;

public class Trip {
	private String title;
	private String departure;
	private String arrival;

	public Trip(String title, String departure, String arrival) {
		this.title = title;
		this.departure = departure;
		this.arrival = arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return String.format("\n%s %s %s\n", title, departure.substring(11, 16), arrival.substring(11, 16));
	}
}