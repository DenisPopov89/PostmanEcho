package ru.netology.test;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class postmanEcho {

    @Test
    void shouldTestSomeData() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("Good Day")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("Good Day"))
        ;
    }

    @Test
    void shouldTestGetArgsFoo1Value() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
        ;
    }

    @Test
    void shouldTestGetArgsFoo2Value() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("some data")
                .when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then()
                .statusCode(200)
                .body("args.foo2", equalTo("bar2"))
        ;
    }

    @Test
    void shouldTestPost() {
        given()
                .baseUri("https://postman-echo.com")
                .body("handWave")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("handWave"))

        ;
    }

    @Test
    void shouldRequestHeaders() {
        given()
                .baseUri("https://postman-echo.com")
                .when()
                .get("/headers")
                .then()
                .statusCode(200)
                .headers("Connection", "keep-alive",
                        "Content-Type", "application/json; charset=utf-8");

    }

    @Test
    void shouldRequestBasicAuth() {
        given()
                .baseUri("https://postman-echo.com")
                .headers("Authorization","Basic cG9zdG1hbjpwYXNzd29yZA==")
                .when()
                .get("/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                ;

    }
}
