package org.example.clientComponent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class SettingsOfClient {

    private JSONParser parser = new JSONParser();
    private String ip = "0.0.0.0";
    private String locationOfLog = "";
    private Long port = 0L;

    public SettingsOfClient readFromJson(String fileWay) {
        try {
            Object obj = parser.parse(new FileReader(fileWay));
            JSONObject jsonObject = (JSONObject) obj;

            ip = (String) jsonObject.get("ip");
            locationOfLog = (String) jsonObject.get("locationOfLog");

            port = (Long) jsonObject.get("port");

        } catch (IOException | ParseException e) {
            System.out.println("Что-то пошло не так");
        }
        return null;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return Math.toIntExact(port);
    }

    public String getLocationOfLog() {
        return locationOfLog;
    }
}
