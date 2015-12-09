package com.github.union.one.bus.api.parser;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.union.one.bus.api.model.Trip;
import com.github.union.one.bus.api.utils.Api;

public class Schedule {
	private List<Trip> trips;

	public Schedule() {
		this.trips = new ArrayList<>();
	}

	public String getSchedule() {
		clean();
		updateParser(trips);
		return convertListToString(trips);
	}

	// пу сути сюда надо передавать код. но API по кодам пунктов нет
	public String getScheduleForToday() {
		Api.updateURL("s9757747", "c10274");
		String scheduleFrom = getSchedule();
		Api.updateURL("c10274", "s9757747");
		String scheduleTo = getSchedule();
		clean();
		return scheduleFrom + "\n" + scheduleTo;
	}

	private void updateParser(List<Trip> trips) {
		try {
			Parser updateParser = new Parser();
			updateParser.parse(trips);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private String convertListToString(List<Trip> trips) {
		String result = "";
		for (Trip trip : trips) {
			if (isNext(trip)) {
				result += trip.toString();
			}
		}
		return result;
	}

	private boolean isNext(Trip trip) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
		simpleDateFormat.format(new Date(System.currentTimeMillis()));

		return Integer.parseInt(trip.getDeparture().substring(11, 13)) >= Integer
				.parseInt(simpleDateFormat.format(new Date(System.currentTimeMillis())).toString());
	}

	private void clean() {
		this.trips.clear();
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	@Override
	public String toString() {
		return "Schedule [trips=" + trips + "]";
	}
}