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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((httpUtils == null) ? 0 : httpUtils.hashCode());
		result = prime * result + ((json == null) ? 0 : json.hashCode());
		result = prime * result + ((jsonObj == null) ? 0 : jsonObj.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((parser == null) ? 0 : parser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Parser other = (Parser) obj;
		if (httpUtils == null) {
			if (other.httpUtils != null)
				return false;
		} else if (!httpUtils.equals(other.httpUtils))
			return false;
		if (json == null) {
			if (other.json != null)
				return false;
		} else if (!json.equals(other.json))
			return false;
		if (jsonObj == null) {
			if (other.jsonObj != null)
				return false;
		} else if (!jsonObj.equals(other.jsonObj))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (parser == null) {
			if (other.parser != null)
				return false;
		} else if (!parser.equals(other.parser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parser [jsonObj=" + jsonObj + ", parser=" + parser + ", object=" + object + ", json=" + json
				+ ", httpUtils=" + httpUtils + "]";
	}
}