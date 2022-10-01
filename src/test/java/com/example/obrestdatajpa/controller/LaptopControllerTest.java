package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @DisplayName("Comprobar findAll desde controladores Spring REST")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Devuelve status 200
        assertEquals(200, response.getStatusCodeValue()); // Devuelve status 200

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());

    }

//    @DisplayName("Comprobar findOneById desde controladores Spring REST")
//    @Test
//    void findOneById() {
//        ResponseEntity <Laptop> response =
//                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(404, response.getStatusCode());
//    }

    @DisplayName("Comprobar Create desde controladores Spring REST")
    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                
    {
        "brand": "Apple",
        "model": "AirBook",
        "screenInches": 13.1,
        "price": 489.99,
        "releaseDate": "2015-11-15",
        "onLine": true
    }
                
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response =testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals("Apple", result.getBrand());

    }

    @DisplayName("Comprobar Update desde controladores Spring REST")
    @Test
    void update() {
    }

    @DisplayName("Comprobar Delete desde controladores Spring REST")
    @Test
    void delete() {
    }

    @DisplayName("Comprobar DeleteAll desde controladores Spring REST")
    @Test
    void deleteAll() {
    }
}