package ru.netology.springbootdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private static GenericContainer<?> devApp = new GenericContainer<>("dev-app-image").withExposedPorts(8080);
    private static GenericContainer<?> prodApp = new GenericContainer<>("prod-app-image").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void devProfileLoads() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Current profile is dev", response.getBody());
    }

    @Test
    void prodProfileLoads() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Current profile is production", response.getBody());
    }
}
