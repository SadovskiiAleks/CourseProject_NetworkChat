package org.example.serverComponent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;

public class Server {

    public static LinkedList<ServerTread> serverList = new LinkedList<>();
    public static MemoryOfMessage memory;
    public static Log log;

    public static void main(String[] args) throws IOException {

        //Считать настройки с файла
        SettingsOfServer settings = new SettingsOfServer();
        settings.readFromJson("settingsServer/settings.json");

        //Настройка подключения к серверу
        ServerSocket server = new ServerSocket(settings.getPort());

        // Логирование данных в fileOfClient1.log
        log = new Log(settings.getLocationOfLog());

        memory = new MemoryOfMessage().setNumberOfMessage(settings.getNumberOfMessageInMemory());
        // Запускаем сервер
        System.out.println("StartServer");
        log.addLog("StartServer " + new Date() + "\n");

        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerTread(socket));
                } catch (IOException e) {
                    socket.close();
                }

            }
        } finally {
            log.logClose();
            server.close();
        }
    }
}
