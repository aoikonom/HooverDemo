package com.rationaldata.hoover.service;

import com.rationaldata.hoover.model.Hoover;
import com.rationaldata.hoover.model.HooverMoveInstructions;
import com.rationaldata.hoover.model.HooverMoveInstructionsJson;
import com.rationaldata.hoover.model.HooverMoveOutput;
import com.rationaldata.hoover.model.Room;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HooverMoveService {

    public HooverMoveOutput move(HooverMoveInstructionsJson instructionsJson) {
        HooverMoveInstructions instructions = HooverMoveInstructions.fromJsonInstructions(instructionsJson);
        Room room = new Room(instructions.roomSize().row(), instructions.roomSize().column(), instructions.dirt());
        Hoover hoover = new Hoover(instructions.initialPosition(), room);
        hoover.addPositionListener(room);

        hoover.executeMoveInstructions(instructions.instructions());

        return new HooverMoveOutput(hoover.getPosition().toIntList(), room.getCleanedDirt());
    }

}
