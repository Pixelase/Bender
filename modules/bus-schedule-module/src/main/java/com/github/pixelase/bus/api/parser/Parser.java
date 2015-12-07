package com.github.pixelase.bus.api.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {
	public void parse(String strJson) {
		JsonObject dataJsonObj = null;
		JsonParser parser = new JsonParser();
		Object object = parser.parse(strJson);

		try {
			dataJsonObj = (JsonObject) object;

			JsonArray threads = dataJsonObj.getAsJsonArray("threads");

			for (int i = 0; i < threads.size(); i++) {
				JsonObject thread = (JsonObject) threads.get(i);
				JsonObject trip = (JsonObject) thread.get("thread");

				JsonElement title = trip.get("title");
				JsonElement arrival = thread.get("arrival");

				System.out.println("title: " + title);
				System.out.println("arrival: " + arrival);
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
		}
	}
}