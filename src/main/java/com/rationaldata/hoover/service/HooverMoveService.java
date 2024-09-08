package com.rationaldata.hoover.service;

import com.rationaldata.hoover.exceptions.ExceptionCodeEnum;
import com.rationaldata.hoover.exceptions.InvalidRequestException;
import com.rationaldata.hoover.model.Dirt;
import com.rationaldata.hoover.model.Hoover;
import com.rationaldata.hoover.model.HooverMoveInstructions;
import com.rationaldata.hoover.model.HooverMoveInstructionsRequest;
import com.rationaldata.hoover.model.HooverMoveResponse;
import com.rationaldata.hoover.model.Room;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HooverMoveService {

    private void validate(HooverMoveInstructionsRequest request) {
        if (request == null)
            throw new InvalidRequestException(ExceptionCodeEnum.REQUEST_NULL);
        if (request.coords() == null || request.coords().size() != 2)
            throw new InvalidRequestException(ExceptionCodeEnum.COORDS_INVALID);
        if (request.roomSize() == null || request.roomSize().size() != 2)
            throw new InvalidRequestException(ExceptionCodeEnum.ROOM_SIZE_INVALID);
        if (request.instructions() == null)
            throw new InvalidRequestException(ExceptionCodeEnum.INSTRUCTIONS_REQUIRED);
        if (request.patches() == null)
            throw new InvalidRequestException(ExceptionCodeEnum.PATCHES_REQUIRED);
    }

    public HooverMoveResponse move(HooverMoveInstructionsRequest request) {
        validate(request);
        HooverMoveInstructions instructions = HooverMoveInstructions.fromJsonInstructions(request);
        Room room = new Room(instructions.roomSize().row(), instructions.roomSize().column());
        Dirt dirt = new Dirt(instructions.dirt());
        Hoover hoover = new Hoover(instructions.initialPosition(), room);
        hoover.addPositionListener(dirt);

        hoover.executeMoveInstructions(instructions.instructions());

        return new HooverMoveResponse(hoover.getPosition().toIntList(), dirt.getCleanedDirt());
    }
}
