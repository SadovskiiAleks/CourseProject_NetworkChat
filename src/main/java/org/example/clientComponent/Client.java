package org.example.clientComponent;

import org.example.serverComponent.Log;

public class Client {
    public static LogClient log;

    public static void main(String[] args) {

        //Считать настройки с файла
        SettingsOfClient settings = new SettingsOfClient();
        settings.readFromJson("settingsClient/settings.json");

        // Логирование данных в file.log
        log = new LogClient(settings.getLocationOfLog());

        new ClientTread(settings.getIp(), settings.getPort());
    }
}
