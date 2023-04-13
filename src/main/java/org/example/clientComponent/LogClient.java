package org.example.clientComponent;

import java.io.FileWriter;
import java.io.IOException;

public class LogClient {
    private String fileWay;
    private String fileName = "fileOfClient1.log";
    FileWriter writer;

    public LogClient(String fileWay) {
        this.fileWay = fileWay;
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
