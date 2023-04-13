package org.example.clientComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteMessage extends Thread {
    private ClientTread clientTread;
    private SimpleDateFormat simpleDateFormat;
    public static LogClient log;

    public WriteMessage(ClientTread clientTread, LogClient log) {
        this.log = log;
        this.clientTread = clientTread;
    }

    @Override
    public void run() {
        while (true) {
            String userWord;

            try {
                clientTread.setTime(new Date());
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                clientTread.setdTime(simpleDateFormat.format(clientTread.getTime()));

                userWord = clientTread.getInputUser().readLine();

                if (userWord.equals("/exit")) {
                    log.addLog("Пользователь покинул чат" + "\n" + "\n");

                    clientTread.getOut().write("/exit");
                    clientTread.getOut().flush();
                    clientTread.downService();
                    break;
                } else {

                    log.addLog("Отправлено - (" + clientTread.getdTime() + ") " + clientTread.getNickname() + ": " + userWord + "\n");

                    clientTread.getOut().write("(" + clientTread.getdTime() + ") " + clientTread.getNickname() + ": " + userWord + "\n");
                }
                clientTread.getOut().flush();
            } catch (IOException e) {
                clientTread.downService();
            }
        }
    }
}
