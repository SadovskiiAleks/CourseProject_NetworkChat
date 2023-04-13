package org.example.clientComponent;

import java.io.IOException;

public class ReadMessage extends Thread {
    private ClientTread clientTread;
    public static LogClient log;

    public ReadMessage(ClientTread clientTread, LogClient log) {
        this.clientTread = clientTread;
        this.log = log;
    }

    @Override
    public void run() {

        String str;
        try {
            while (true) {
                str = clientTread.getIn().readLine();

                if (str.equals("/exit")) {
                    clientTread.downService();
                    break;
                }
                log.addLog("Получено сообщение - " + str + "\n");
                System.out.println(str);
            }
        } catch (IOException e) {
            clientTread.downService();
        }
    }
}
