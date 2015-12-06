package com.github.mickevichyura.grsu.api.utils;

import java.text.SimpleDateFormat;

public interface DateFormat {
	static String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
	
	static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
}
