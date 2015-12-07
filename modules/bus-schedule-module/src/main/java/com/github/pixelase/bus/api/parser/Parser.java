package com.github.pixelase.bus.api.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {
	
    protected void parse(String strJson) {
        
        // выводим целиком полученную json-строку
        System.out.println(strJson);

        JsonObject dataJsonObj = null;
        JsonElement secondName;
        
        JsonParser parser = new JsonParser();
        
        Object object = parser.parse(strJson);
        

        try {
            /*dataJsonObj = (JsonObject) object;
            
            JsonArray friends = dataJsonObj.getAsJsonArray("friends");

            // 1. достаем инфо о втором друге - индекс 1
            JsonObject secondFriend = (JsonObject) friends.get(1);
            secondName = secondFriend.get("name");
            System.out.println("Второе имя: " + secondName);

            // 2. перебираем и выводим контакты каждого друга
            for (int i = 0; i < friends.size(); i++) {
                JsonObject friend = (JsonObject) friends.get(i);

                JsonObject contacts = (JsonObject) friend.get("contacts");

                JsonElement phone = contacts.get("mobile");
                JsonElement email = contacts.get("email");
                JsonElement skype = contacts.get("skype");
                
                System.out.println("phone: " + phone);
                System.out.println("email: " + email);
                System.out.println("skype: " + skype);
            }*/

        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }
}

