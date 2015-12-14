package com.github.union.one.bus.api.parser;

import java.net.MalformedURLException;
import java.util.List;

import com.github.union.one.bus.api.core.Parser;
import com.github.union.one.bus.api.model.Trip;
import com.github.union.one.bus.api.utils.Api;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class YandexApiParser extends Parser<Trip> {
	private JsonArray threads;

	public YandexApiParser() throws MalformedURLException {
		super(Api.URL);
		this.threads = super.getJsonObj().getAsJsonArray("threads");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parse(List<Trip> trips) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < threads.size(); i++) {
				JsonObject thread = (JsonObject) this.threads.get(i);
				JsonObject trip = (JsonObject) thread.get("thread");
				trips.add(new Trip(trip.get("title").getAsString(), thread.get("departure").getAsString(),
						thread.get("arrival").getAsString()));
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
		}
	}

	public JsonArray getThreads() {
		return threads;
	}

	public void setThreads(JsonArray threads) {
		this.threads = threads;
	}

	@Override
	public String toString() {
		return "YandexApiParser [threads=" + threads + "]";
	}
}