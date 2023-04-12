package org.example.serverComponent;

import java.io.*;
import java.net.Socket;

public class ServerTread extends Thread {

    private Socket socket;

    private BufferedReader readIn;
    private BufferedWriter writeOut;

    public ServerTread(Socket socket) throws IOException {
        this.socket = socket;

        readIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writeOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //Печать истории
        Server.memory.printMemory(writeOut);
        start();

    }

    @Override
    public void run() {
        String word;
        String nameOnTread;
        try {
            //Первое сообщение это имя пользователя
            word = readIn.readLine();
            nameOnTread =  word.toString().replace("Здравствуйте: ","");
            try {
                writeOut.write(word + "\n");
                writeOut.flush();
                Server.log.addLog("Пользователь с именем _" + nameOnTread + "_ подключился к серверу" + "\n");
            } catch (IOException e) {
                Server.log.addLog("Проблемы с чтением имени клиента"+ "\n");
            }

            try {
                while (true) {
                    word = readIn.readLine();
                    if (word.equals("/exit")) {
                        Server.log.addLog("Пользователь _" + nameOnTread + "_ покинул сервер"+ "\n");
                        this.downService();

                        break;
                    }

                    Server.log.addLog("Добавлено сообщение - " + word+ "\n");
                    System.out.println("Echoing: " + word);
                    Server.memory.addMessageToMemory(word);

                    for (ServerTread s : Server.serverList) {
                        if(!s.equals(this)) {
                            s.send(word);
                        }
                    }
                }
            } catch (NullPointerException e) {
            }
        } catch (IOException e) {
            this.downService();
        }
    }

    private void send(String msg) {
        try {
            writeOut.write(msg + "\n");
            writeOut.flush();
        } catch (IOException e) {

        }
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                readIn.close();
                writeOut.close();
                for (ServerTread s : Server.serverList) {
                    if (s.equals(this)) s.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException e) {

        }
    }
}
