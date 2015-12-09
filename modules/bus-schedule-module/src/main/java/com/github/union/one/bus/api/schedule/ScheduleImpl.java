package com.github.union.one.bus.api.schedule;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.union.one.bus.api.model.Trip;
import com.github.union.one.bus.api.parser.ParserImpl;
import com.github.union.one.bus.api.utils.Api;

public class ScheduleImpl implements Schedule {
	private List<Trip> trips;

	public ScheduleImpl() {
		this.trips = new ArrayList<>();
	}
	
	// пу сути сюда надо передавать код. но API по кодам пунктов нет
	@Override
	public String getSchedule(String request) {
		Api.updateURL("s9757747", "c10274");
		String scheduleFrom = updateSchedule(request);
		Api.updateURL("c10274", "s9757747");
		String scheduleTo = updateSchedule(request);
		//clean();
		return scheduleFrom + "\n" + scheduleTo;
	}
	
	private String updateSchedule(String request) {
		clean();
		updateParser(trips);
		return convertListToString(trips, request);
	}

	private void updateParser(List<Trip> trips) {
		try {
			ParserImpl updateParser = new ParserImpl();
			updateParser.parse(trips);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private String convertListToString(List<Trip> trips, String request) {
		if ("now".equals(request)) {
			return convertListToStringWithNext(trips);
		} else {
			return convertListToStringWithoutNext(trips);
		}
	}
	
	private String convertListToStringWithNext(List<Trip> trips) {
		String result = "";
		for (Trip trip : trips) {
			if (isNext(trip)) {
				result += trip.toString();
			}
		}
		return result;
	}

	private String convertListToStringWithoutNext(List<Trip> trips) {
		String result = "";
		for (Trip trip : trips) {
			result += trip.toString();
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