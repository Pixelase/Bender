package com.github.pixelase.bus.api.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	public static final int READ_TIMEOUT_MILLIS = 10000;
	public static final int CONNECTION_TIMEOUT_MILLIS = 15000;

	public static InputStream getInputStream(String myUrl) throws IOException {
		URL url = new URL(myUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setReadTimeout(READ_TIMEOUT_MILLIS);
		connection.setConnectTimeout(CONNECTION_TIMEOUT_MILLIS);
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		// Starts the query
		connection.connect();
		
		return connection.getInputStream();
	}

	public static void close(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.toString());
		}
	}
}