import org.example.clientComponent.SettingsOfClient;
import org.example.serverComponent.MemoryOfMessage;
import org.example.serverComponent.SettingsOfServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.StringWriter;

public class TestClient {
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
        SettingsOfClient settings = new SettingsOfClient();
        settings.readFromJson("settingsClient/settingsClient1.json");

        // when:
        String answer = settings.getIp();

        // then:
        Assertions.assertEquals("localhost",answer);
    }

    @Test
    public void testTwo() {

        // given:
        SettingsOfClient settings = new SettingsOfClient();
        settings.readFromJson("settingsClient/settingsClient1.json");

        // when:
        String answer = settings.getIp();

        // then:
        Assertions.assertEquals("localhost",answer);
    }






}
