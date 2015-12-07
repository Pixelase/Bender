package com.github.pixelase.bus.api.parser;

import java.net.MalformedURLException;
import java.util.List;

import com.github.pixelase.bus.api.model.Trip;
import com.github.pixelase.bus.api.utils.HttpUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
 * Parser has method parse, that should be convert needed parts of Json
 * to list of my own class Trip with some fields.
 */

public class Parser {
	private JsonObject jsonObj;
	private JsonParser parser;
	private Object object;
	private String json;
	private HttpUtils httpUtils;
	private JsonArray threads;

	public Parser() throws MalformedURLException {
		this.httpUtils = new HttpUtils();
		this.json = this.httpUtils.getJson();
		this.parser = new JsonParser();
		this.object = parser.parse(this.json);
		this.jsonObj = (JsonObject) object;
		this.threads = jsonObj.getAsJsonArray("threads");

	}

	public JsonObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JsonObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	public JsonParser getParser() {
		return parser;
	}

	public void setParser(JsonParser parser) {
		this.parser = parser;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public HttpUtils getHttpUtils() {
		return httpUtils;
	}

	public void setHttpUtils(HttpUtils httpUtils) {
		this.httpUtils = httpUtils;
	}

	public void parse(List<Trip> trips) {
		try {
			for (int i = 0; i < threads.size(); i++) {
				JsonObject thread = (JsonObject) this.threads.get(i);
				JsonObject trip = (JsonObject) thread.get("thread");
				trips.add(new Trip(trip.get("title").getAsString(), thread.get("arrival").getAsString()));
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
		}
	}
}