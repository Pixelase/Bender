package com.github.union.one.bus.api.core;

import java.net.MalformedURLException;
import java.util.List;

import com.github.union.one.bus.api.utils.HttpUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class Parser<T> {
	private JsonObject jsonObj;
	private JsonParser parser;
	private Object object;
	private String json;
	private HttpUtils httpUtils;

	public Parser(String url) throws MalformedURLException {
		this.httpUtils = new HttpUtils(url);
		this.json = this.httpUtils.getJson();
		this.parser = new JsonParser();
		this.object = parser.parse(this.json);
		this.jsonObj = (JsonObject) object;
	}

	public abstract void parse(List<T> trips);

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

	@Override
	public String toString() {
		return "Parser [jsonObj=" + jsonObj + ", parser=" + parser + ", object=" + object + ", json=" + json
				+ ", httpUtils=" + httpUtils + "]";
	}
}