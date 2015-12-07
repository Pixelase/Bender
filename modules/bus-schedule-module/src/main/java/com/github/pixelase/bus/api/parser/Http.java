package com.github.pixelase.bus.api.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.pixelase.bus.api.utils.Api;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Http {
	HttpURLConnection urlConnection = null;
	BufferedReader reader = null;
	String resultJson = "";

	public String getJson() {
		// получаем данные с внешнего ресурса
		try {
			URL url = new URL("http://androiddocs.ru/api/friends.json");

			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();

			InputStream inputStream = urlConnection.getInputStream();
			StringBuffer buffer = new StringBuffer();

			reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			resultJson = buffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
	
    protected void onPostExecute(String strJson) {
        
        // выводим целиком полученную json-строку
        System.out.println(strJson);

        JsonObject dataJsonObj = null;
        JsonElement secondName;
        
        JsonParser parser = new JsonParser();
        
        Object object = parser.parse(strJson);
        

        /*try {
            dataJsonObj = (JsonObject) object;
            
            JsonArray friends = dataJsonObj.getAsJsonArray("friends");

            // 1. достаем инфо о втором друге - индекс 1
            JsonObject secondFriend = (JsonObject) friends.get(1);
            secondName = secondFriend.get("name");
            System.out.println("Второе имя: " + secondName);

            // 2. перебираем и выводим контакты каждого друга
            /*for (int i = 0; i < friends.length(); i++) {
                JSONObject friend = friends.getJSONObject(i);

                JSONObject contacts = friend.getJSONObject("contacts");

                String phone = contacts.getString("mobile");
                String email = contacts.getString("email");
                String skype = contacts.getString("skype");

                System.out.println("phone: " + phone);
                System.out.println("email: " + email);
                System.out.println("skype: " + skype);
            }*/

        /*} catch (JsonIOException e) {
            e.printStackTrace();
        }*/
    }
}

