package com.rationaldata.hoover.model;

import com.rationaldata.hoover.exceptions.InvalidInputException;
import jakarta.ws.rs.POST;
import org.jboss.logging.annotations.Pos;

import java.util.List;

public record Position(int row, int column) {

    static public Position fromIntList(List<Integer> cords) {
        if (cords == null || cords.size() != 2)
            throw new InvalidInputException("Cords should consist of 2 integers");
        return new Position(cords.get(0), cords.get(1));
    }
    static public Position fromIntArray(int[] cords) {
        if (cords == null || cords.length != 2)
            throw new InvalidInputException("Cords should consist of 2 integers");
        return new Position(cords[0], cords[1]);
    }
    public Position moveBy(Step step) {
        return new Position(this.row + step.difRow(), this.column + step.difColumn());
    }

    public List<Integer> toIntList() {
        return List.of(row, column);
    }
}
