package com.rationaldata.hoover;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import com.rationaldata.hoover.exceptions.ExceptionCodeEnum;
import com.rationaldata.hoover.exceptions.ExceptionResponse;
import com.rationaldata.hoover.model.HooverMoveInstructions;
import com.rationaldata.hoover.model.HooverMoveInstructionsRequest;
import com.rationaldata.hoover.model.HooverMoveResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
        HooverMoveResponse expectedResponse = new HooverMoveResponse(List.of(1, 3), 1);
        validTest(request, expectedResponse);
    }

    @Test
    public void testValidExampleWithWallHits() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,5),
                List.of(1, 2), List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3}), "NNNESSWWWSS");
        HooverMoveResponse expectedResponse = new HooverMoveResponse(List.of(0, 0), 2);
        validTest(request, expectedResponse);
    }

    @Test
    public void testValidExampleWithDirtAtInitialPosition() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,4),
                List.of(1, 2), List.of(new int[]{1, 0}, new int[]{1, 2}, new int[]{2, 2}, new int[]{2, 3}), "NNNESSWWWSS");
        HooverMoveResponse expectedResponse = new HooverMoveResponse(List.of(0, 0), 3);
        validTest(request, expectedResponse);
    }

    @Test
    public void testInvalidPatches() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,5),
                List.of(1, 2), List.of(new int[]{1, 0}, new int[]{2, 2, 5}, new int[]{2, 3}), "NNESEESWNWW");
        ExceptionResponse expectedResponse = new ExceptionResponse(ExceptionCodeEnum.PATCHES_INVALID);
        invalidTest(request, expectedResponse, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testInvalidInitialPosition() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,4),
                List.of(1, 4), List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3}), "NNESEESWNWW");
        ExceptionResponse expectedResponse = new ExceptionResponse(ExceptionCodeEnum.UNKNWON_INSTRUCTION);
        invalidTest(request, expectedResponse, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testInvalidInstruction() {
        HooverMoveInstructionsRequest request = new HooverMoveInstructionsRequest(List.of(5,4),
                List.of(1, 3), List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3}), "NEA");
        ExceptionResponse expectedResponse = new ExceptionResponse(ExceptionCodeEnum.INVALID_INSTRUCTION);
        invalidTest(request, expectedResponse, HttpStatus.SC_BAD_REQUEST);
    }

    void validTest(HooverMoveInstructionsRequest request,HooverMoveResponse expectedResponse) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(HOOVER_MOVE_ENDPOINT)
                .thenReturn();


        HooverMoveResponse actualResponse = response.getBody().as(HooverMoveResponse.class);

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    void invalidTest(HooverMoveInstructionsRequest request,ExceptionResponse expectedResponse,int expectedStatus) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(HOOVER_MOVE_ENDPOINT)
                .thenReturn();


        ExceptionResponse actualResponse = response.as(ExceptionResponse.class);

        Assertions.assertEquals(expectedStatus, response.getStatusCode());
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
