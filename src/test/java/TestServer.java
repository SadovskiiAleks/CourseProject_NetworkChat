import org.example.clientComponent.ClientTread;
import org.example.serverComponent.MemoryOfMessage;
import org.example.serverComponent.Server;
import org.example.serverComponent.ServerTread;
import org.example.serverComponent.SettingsOfServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class TestServer {
    @BeforeAll
    public static void before() {
        System.out.println("Началось тестирование!");
    }

    @AfterAll
    public static void after() {
        System.out.println("Тестирование успешно" +
                " завершено, вы молодец!");
    }

    @Test
    public void testOne() {

        // given:
        SettingsOfServer settings = new SettingsOfServer();
        settings.readFromJson("settingsServer/settings.json");

        // when:
        String answer = settings.getIp();

        // then:
        Assertions.assertEquals("localhost",answer);
    }


    //Тестирование добавление потока в лист
    public static LinkedList<ServerTread> serverList = new LinkedList<>();
    MemoryOfMessage memory;
    @Test
    public void testTwo() throws InterruptedException {

        // given:
        class Server extends Thread {
            @Override
            public void run() {
                try {
                    memory = new MemoryOfMessage().setNumberOfMessage(10);
                    ServerSocket server = new ServerSocket(8010);
                    Socket socket = server.accept();
                    try {
                        serverList.add(new ServerTread(socket));
                    } catch (IOException e) {
                        socket.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        class ThreadClient extends Thread {
            @Override
            public void run() {
                ClientTread clientTread = new ClientTread("localhost", 8010);
            }
        }


        Server threadServer = new Server();
        threadServer.start();


        ThreadClient threadClient = new ThreadClient();
        threadClient.start();

        threadClient.join();

        // when:
        int caseOfSocket = serverList.size();

        // then:
        Assertions.assertEquals(0,caseOfSocket);
    }

    //Проверка записи сообщении в память
    @Test
    public void testThree() {

        // given:
        MemoryOfMessage memoryOfMessage = new MemoryOfMessage();
        memoryOfMessage.addMessageToMemory("One");
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bew = new BufferedWriter(stringWriter);

        // when:
        memoryOfMessage.printMemory(bew);

        // then:
        Assertions.assertEquals("История сообщений\n" +
                "One\n" +
                "___________________\n",
                stringWriter.toString());
    }

}
