package com.github.union.one.bus.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Api {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static final String BASE_URL = "https://api.rasp.yandex.net/v1.0/search/";

	private static final String API_KEY = "d4debfc5-bb40-49df-ae49-3113eb0707f9";

	public static final String FORMAT = "format=json";

	public static String FROM = "s9757747";

	public static String TO = "c10274";

	public static final String LANG = "lang=ru";

	public static final String PAGE = "page=1";

	public static String DATE = "date=" + simpleDateFormat.format(new Date(System.currentTimeMillis()));;

	public static void updateURL(String from, String to) {
		Api.URL = BASE_URL + "?apikey=" + API_KEY + "&" + FORMAT + "&from=" + from + "&to=" + to + "&" + LANG + "&"
				+ PAGE + "&" + DATE;
	}

	public static String URL = BASE_URL + "?apikey=" + API_KEY + "&" + FORMAT + "&from=" + FROM + "&to=" + TO + "&"
			+ LANG + "&" + PAGE + "&" + DATE;
	
	public static String CODES_URL = "https://gist.githubusercontent.com/UnionOne/cadac50d1e4c38af3d8f/raw/604f06651d2954506bcbf4e735eb59475a40578e/codes.json";
}