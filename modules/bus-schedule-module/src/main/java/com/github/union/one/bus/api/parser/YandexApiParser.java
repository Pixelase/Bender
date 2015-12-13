package com.github.union.one.bus.api.parser;

import java.net.MalformedURLException;
import java.util.List;

import com.github.union.one.bus.api.core.Parser;
import com.github.union.one.bus.api.model.Trip;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class YandexApiParser extends Parser {
	private JsonArray threads;

	public YandexApiParser() throws MalformedURLException {
		super();
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((threads == null) ? 0 : threads.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		YandexApiParser other = (YandexApiParser) obj;
		if (threads == null) {
			if (other.threads != null)
				return false;
		} else if (!threads.equals(other.threads))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "YandexApiParser [threads=" + threads + "]";
	}
}