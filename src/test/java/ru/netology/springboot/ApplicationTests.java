package ru.netology.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    private static final String HOST = "http://localhost:";
    private static final int PORT_DEV = 8080;
    private static final int PORT_PROD = 8081;
    private static final String ENDPOINT = "/profile";

    @Autowired
    TestRestTemplate restTemplate;


    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(PORT_DEV);

    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(PORT_PROD);

    @BeforeAll
    public static void setUp() {
       devapp.start();
        prodapp.start();

    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forDev = restTemplate.getForEntity(HOST + devapp.getMappedPort(PORT_DEV) + ENDPOINT, String.class);
        System.out.println(forDev.getBody());
        Assertions.assertEquals("Current profile is dev", forDev.getBody());

        ResponseEntity<String> prod = restTemplate.getForEntity(HOST + prodapp.getMappedPort(PORT_PROD) + ENDPOINT, String.class);
        System.out.println(prod.getBody());
        Assertions.assertEquals("Current profile is production", prod.getBody());
    }
}
