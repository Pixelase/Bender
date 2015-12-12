package com.github.union.one.bus.api.parser;

import java.util.List;

import com.github.union.one.bus.api.model.Trip;

public interface Parser {
	void parse(List<Trip> trips);
}