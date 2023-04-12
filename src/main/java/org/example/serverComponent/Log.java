package org.example.serverComponent;

import java.io.*;

public class Log {
    private String fileWay;
    private String fileName = "fileOfServer.log";
    FileWriter writer;

    public Log(String fileWay) {


        this.fileWay = fileWay + fileName;
        try {
            FileWriter writer = new FileWriter(this.fileWay, true);
            this.writer = writer;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLog(String massage) throws IOException {
        writer.write(massage);
        writer.flush();
    }

    public void logClose() throws IOException {
        writer.close();
    }
}
