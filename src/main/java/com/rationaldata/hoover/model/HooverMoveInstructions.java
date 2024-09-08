package com.rationaldata.hoover.model;

import java.util.Set;
import java.util.stream.Collectors;

public record HooverMoveInstructions(Position roomSize, Position initialPosition, Set<Position> dirt,String instructions) {
    static public HooverMoveInstructions fromJsonInstructions(HooverMoveInstructionsRequest instructionsJson) {
        return new HooverMoveInstructions(Position.fromIntList(instructionsJson.roomSize()),
                Position.fromIntList(instructionsJson.coords()),
                instructionsJson.patches().stream().map(Position::fromIntArray).collect(Collectors.toSet()),
                instructionsJson.instructions());
    }
}
