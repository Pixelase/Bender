package com.github.union.one.bus.api.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.github.union.one.bus.api.model.Code;

public class Reader {
	private String path;

	public Reader(String path) {
		this.path = path;
	}

	public void read(List<Code> codes) {
		String str;

		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			while ((str = br.readLine()) != null) {
				str.trim();
				String[] array = str.split("-");
					codes.add(new Code(array[0], array[1]));
			}
		} catch (IOException e) {
			System.out.println("Mothertrucker: " + e);
		}
	}
}