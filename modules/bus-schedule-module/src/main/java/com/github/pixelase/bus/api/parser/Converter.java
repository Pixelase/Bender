package com.github.pixelase.bus.api.parser;

import java.net.MalformedURLException;

import com.github.pixelase.bus.api.utils.HttpUtils;

public class Converter {
	public static void main(String[] args) throws MalformedURLException {
		HttpUtils httpUtils = new HttpUtils();
		String json = httpUtils.getJson();
		Parser parser = new Parser();
		
		parser.parse(json);
	}
}