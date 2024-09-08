package com.rationaldata.hoover.api;

import com.rationaldata.hoover.model.HooverMoveInstructionsRequest;
import com.rationaldata.hoover.model.HooverMoveResponse;
import com.rationaldata.hoover.service.HooverMoveService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hoover")
public class HooverMoveApi {

    @Inject
    HooverMoveService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/move")
    public HooverMoveResponse move(HooverMoveInstructionsRequest instructions) {
        return service.move(instructions);
    }

}