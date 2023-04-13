package org.example.clientComponent;


import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientTread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    private BufferedReader inputUser;
    private String ipUser;
    private int port;

    private String nickname;
    private Date time;
    private String dTime;

    public static LogClient log;

    public ClientTread(String ipUser, int port, LogClient log) {

        this.ipUser = ipUser;
        this.port = port;

        this.log = log;

        try {
            this.socket = new Socket(ipUser, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }

        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.pressNickname();
            new ReadMessage(this, log).start();
            new WriteMessage(this, log).start();

        } catch (IOException e) {

            ClientTread.this.downService();
        }
    }

    private void pressNickname() {

        System.out.println("Введите свое имя: ");
        try {
            nickname = inputUser.readLine();
            log.addLog("Пользователь _" + nickname + "_ зашел в чат " + new Date() + "\n");
            out.write("Здравствуйте: " + nickname + "\n");
            out.flush();
        } catch (IOException e) {
        }
    }

    protected void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
        }
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public BufferedReader getIn() {
        return in;
    }


    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }


    public BufferedWriter getOut() {
        return out;
    }


    public String getNickname() {
        return nickname;
    }


    public BufferedReader getInputUser() {
        return inputUser;
    }

}
