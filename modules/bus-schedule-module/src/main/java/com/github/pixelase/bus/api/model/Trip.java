package com.github.pixelase.bus.api.model;

public class Trip {
	private String arrival;
	private Point from;
	private Point to;

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Point getFrom() {
		return from;
	}

	public void setFrom(Point from) {
		this.from = from;
	}

	public Point getTo() {
		return to;
	}

	public void setTo(Point to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Trip [arrival=" + arrival + ", from=" + from + ", to=" + to + "]";
	}
}