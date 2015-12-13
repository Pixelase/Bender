package com.github.union.one.bus.api.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.union.one.bus.api.model.Code;

public class Converter {
	static final String TXT_FILE = "";

	static final String JSON_FILE = "";

	public static <T> void printList(List<T> items) {
		for (T item : items) {
			System.out.println(item);
		}
	}

	public static void main(String[] args) throws IOException {
		List<Code> codes = new ArrayList<>();
		Reader reader = new Reader(TXT_FILE);
		reader.read(codes);
		System.out.println(codes.toString());

		final String dbPath = JSON_FILE;
		CodeManager manager = new CodeManager(dbPath);

		if (!manager.readBase()) {
			manager.setCodes(codes);
			manager.setDbPath(dbPath);
		}

		System.out.println("\n\nOriginal persons:");
		printList(manager.getCodes());

		manager.updateOrCreateBase();
	}
}