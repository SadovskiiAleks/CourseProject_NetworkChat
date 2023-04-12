package org.example.serverComponent;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class MemoryOfMessage {

    private LinkedList<String> memory = new LinkedList<>();
    private int numberOfMessage = 10;

    public void addMessageToMemory(String message) {
        if (memory.size() >= 10) {
            memory.removeFirst();
            memory.add(message);
        } else {
            memory.add(message);
        }
    }

    public void printMemory(BufferedWriter writer) {
        if (memory.size() > 0) {
            try {
                writer.write("История сообщений" + "\n");
                for (String s : memory) {
                    writer.write(s + "\n");
                }
                writer.write("___________________" + "\n");
                writer.flush();
            } catch (IOException e) {
            }
        }
    }

    public MemoryOfMessage setNumberOfMessage(int numberOfMessage) {
        this.numberOfMessage = numberOfMessage;
        return this;
    }
}
