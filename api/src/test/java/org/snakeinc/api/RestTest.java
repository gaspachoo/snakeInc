package org.snakeinc.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

//@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest {

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1";
    }

    @Test
    public void testPost() {
        PlayerParams params = new PlayerParams("nom", 21);

        given()
            .contentType(JSON)
            .body(params)
            .when()
                .post("/players")
            .then()
                .statusCode(200)
                .body("name", equalTo("nom"))
                .body("age", equalTo(21));
    }

    @Test
    public void testGet() {
        PlayerParams params = new PlayerParams("nom", 21);

        Player created =
            given()
                .contentType(JSON)
                .body(params)
            .when()
                .post("/players")
            .then()
                .statusCode(200)
                .extract()
                .as(Player.class);

        given()
        .when()
            .get("/players/{id}", created.getId())
        .then()
            .statusCode(200)
            .body("name", equalTo(created.getName()))
            .body("age", equalTo(created.getAge()));
    }
}
