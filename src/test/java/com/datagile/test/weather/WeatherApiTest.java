package com.datagile.test.weather;

import com.datagile.test.util.ParserToFile;
import com.datagile.test.weather.deserialized.ResponseRepresentation;
import com.datagile.test.util.ConfProperties;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WeatherApiTest {
    private ParserToFile parserToFile;
    @Test
    public void testGetRequest() {
        ResponseRepresentation response = RestAssured
                .given()
                .queryParam("key", ConfProperties.getProperty("key"))
                .queryParam("q", ConfProperties.getProperty("location"))
                .when()
                .get(ConfProperties.getProperty("weather.api.endpoint"))
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(ResponseRepresentation.class);
        parserToFile = new ParserToFile();
        parserToFile.writeToFile(response);
    }
}
