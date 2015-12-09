package com.github.union.one.bus.api.model;

public class Trip {
	private String title;
	private String arrival;

	public Trip() {

	}

	public Trip(String title, String arrival) {
		this.title = title;
		this.arrival = arrival;
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
		return "\n" + title + "  " + arrival;
	}
}