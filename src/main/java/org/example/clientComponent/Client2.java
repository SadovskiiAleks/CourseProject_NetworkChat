package org.example.clientComponent;

public class Client2 {
    public static LogClient log;

    public static void main(String[] args) {

        //Считать настройки с файла
        SettingsOfClient settings = new SettingsOfClient();
        settings.readFromJson("settingsClient/settingsClient2.json");

        // Логирование данных в fileOfClient1.log
        log = new LogClient(settings.getLocationOfLog());

        new ClientTread(settings.getIp(), settings.getPort(), log);
    }
}
