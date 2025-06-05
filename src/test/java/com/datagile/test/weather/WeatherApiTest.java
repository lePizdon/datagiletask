package com.datagile.test.weather;

import com.datagile.test.weather.deserialized.ResponseRepresentation;
import com.datagile.test.util.ConfProperties;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WeatherApiTest {
    @Test
    public void testGetRequest() {
        ResponseRepresentation response = RestAssured
                .given()
                .queryParam("key", ConfProperties.getProperty("key"))
                .queryParam("q", ConfProperties.getProperty("location"))
                .when()
                .get(ConfProperties.getProperty("weather.api.endpoint"))
                .then()
                .statusCode(200)
                .extract()
                .as(ResponseRepresentation.class);
        Path outputFile = Path.of("files/output.txt");

        if (!Files.exists(outputFile)) {
            try {
                Files.createFile(outputFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalStateException("Directory must be made at the root of the project first");
            }
        }
        if (Files.isWritable(outputFile)) {
            try {
                Files.writeString(outputFile, response.toString());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Couldn't write to file. Check locks");
            }
        }
    }
}
