package com.github.pixelase.bus.api.parser;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.github.pixelase.bus.api.model.Trip;

public class ConsoleChecker {
	public static void main(String[] args) throws MalformedURLException {
		List<Trip> trips = new ArrayList<>();
		Parser parser = new Parser();
		
		parser.parse(trips);
		System.out.println(trips.toString());
	}
	
	public static String result() throws MalformedURLException {
		List<Trip> trips = new ArrayList<>();
	
		Parser parser = new Parser();
		parser.parse(trips);
		
		return trips.toString();
	}
}