package com.github.union.one.bus.api.parser;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.github.union.one.bus.api.model.Trip;

public class Schedule {
	private List<Trip> trips;
	private Parser parser;

	public Schedule() {
		this.trips = new ArrayList<>();
		try {
			this.parser = new Parser();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getSchedule() {
		this.trips.clear();
		this.parser.parse(this.trips);

		String result = "";

		for (Trip trip : trips) {
			result += trip.toString();
		}
		return result;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	@Override
	public String toString() {
		return "Schedule [trips=" + trips + ", parser=" + parser + "]";
	}
}