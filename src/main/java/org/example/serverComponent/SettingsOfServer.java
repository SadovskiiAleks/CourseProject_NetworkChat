package org.example.serverComponent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class SettingsOfServer {

    private JSONParser parser = new JSONParser();
    private String ip = "188.243.183.66";
    private String locationOfLog = "";
    private Long port = 0L;
    private Long numberOfMessageInMemory = 0L;
    private Array[] setOne = new Array[2];
    private boolean setTwo = false;

    public SettingsOfServer readFromJson(String fileWay) {
        try {
            Object obj = parser.parse(new FileReader(fileWay));
            JSONObject jsonObject = (JSONObject) obj;

            ip = (String) jsonObject.get("ip");
            locationOfLog = (String) jsonObject.get("locationOfLog");

            port = (Long) jsonObject.get("port");
            numberOfMessageInMemory = (Long) jsonObject.get("numberOfMessageInMemory");
            JSONArray setOne = (JSONArray) jsonObject.get("setOne");
            setTwo = (boolean) jsonObject.get("setTwo");
            int i = 0;
            for (Object coins : setOne) {
                setOne.set(i, coins);
                i++;
            }

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

    public int getNumberOfMessageInMemory() {
        return Math.toIntExact(numberOfMessageInMemory);
    }

    public String getLocationOfLog() {
        return locationOfLog;
    }
}
