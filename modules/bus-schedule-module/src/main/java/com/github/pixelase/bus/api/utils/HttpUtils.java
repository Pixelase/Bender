package com.github.pixelase.bus.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * HttpUtils get connection with resource by setting api url
 * So than this city wok return Json 
 */

public class HttpUtils {
	private HttpURLConnection httpURLConnection;
	private BufferedReader bufferedReader;
	private String resultJson;
	private URL url;

	private void urlInit() throws MalformedURLException {
		//this.url = new URL(Api.EXAMPLE_URL);
		this.url = new URL(Api.BASE_URL);
	}

	private void urlConnectionInit() throws IOException {
		this.httpURLConnection = (HttpURLConnection) url.openConnection();
		this.httpURLConnection.setRequestMethod("GET");
		this.httpURLConnection.connect();
	}

	private void bufferedReaderInit() throws IOException {
		InputStream inputStream = httpURLConnection.getInputStream();
		this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	}
	
	public HttpUtils() throws MalformedURLException {
		try {
			urlInit();
			urlConnectionInit();
			bufferedReaderInit();
			this.resultJson = "";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getJson() {
		try {
			StringBuffer buffer = new StringBuffer();
			String line;

			while ((line = this.bufferedReader.readLine()) != null) {
				buffer.append(line);
			}
			this.resultJson = buffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.resultJson;
	}
}