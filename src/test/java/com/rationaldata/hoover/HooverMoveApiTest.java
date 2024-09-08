package com.rationaldata.hoover;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.rationaldata.hoover.exceptions.ExceptionCodeEnum;
import com.rationaldata.hoover.exceptions.ExceptionResponse;
import com.rationaldata.hoover.model.HooverMoveInstructionsRequest;
import com.rationaldata.hoover.model.HooverMoveResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.ws.rs.core.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HooverMoveApiTest {

    private static final String HOOVER_MOVE_ENDPOINT = "api/v1/hoover/move";

    @Test
    public void testValidExample() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,5),
                List.of(1, 2), List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3}), "NNESEESWNWW");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(HOOVER_MOVE_ENDPOINT)
                .thenReturn();


        HooverMoveResponse actualResponse = response.getBody().as(HooverMoveResponse.class);
        HooverMoveResponse expectedResponse = new HooverMoveResponse(List.of(1, 3), 1);

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testInvalidPatches() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,5),
                List.of(1, 2), List.of(new int[]{1, 0}, new int[]{2, 2, 5}, new int[]{2, 3}), "NNESEESWNWW");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(HOOVER_MOVE_ENDPOINT)
                .thenReturn();


        ExceptionResponse actualResponse = response.as(ExceptionResponse.class);
        ExceptionResponse expectedResponse = new ExceptionResponse(ExceptionCodeEnum.PATCHES_INVALID);

        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void testInvalidInitialPosition() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,4),
                List.of(1, 4), List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3}), "NNESEESWNWW");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(HOOVER_MOVE_ENDPOINT)
                .thenReturn();


        ExceptionResponse actualResponse = response.as(ExceptionResponse.class);
        ExceptionResponse expectedResponse = new ExceptionResponse(ExceptionCodeEnum.INVALID_INITIAL_POSITION);

        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(actualResponse, expectedResponse);
    }
}
