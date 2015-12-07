package com.github.pixelase.bus.api.parser;

import java.net.MalformedURLException;

import com.github.pixelase.bus.api.utils.HttpUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Converter {
	public static void main(String[] args) throws MalformedURLException {
		
		/*String json = "{paramsArray: [\"first\", 100], "
	            + "paramsObj: {one: \"two\", three: \"four\"},"
	            + "paramsStr: \"some string\"}";
		
		JsonParser parser = new JsonParser();
		
		Object object = parser.parse(json);
		JsonObject jsonObject = (JsonObject) object;
		System.out.println(jsonObject.get("paramsArray"));*/
		HttpUtils httpUtils = new HttpUtils();
		String json = httpUtils.getJson();
		Http http = new Http();
		//String json = http.getJson();
		http.onPostExecute(json);
	}
}
