package com.rationaldata.hoover.model;

import com.rationaldata.hoover.exceptions.ExceptionCodeEnum;
import com.rationaldata.hoover.exceptions.InvalidRequestException;

import java.util.List;

public record Position( int column,int row) {

    static public Position fromIntList(List<Integer> coords) {
        if (coords == null || coords.size() != 2)
            throw new InvalidRequestException(ExceptionCodeEnum.PATCHES_INVALID);
        return new Position(coords.get(0), coords.get(1));
    }
    static public Position fromIntArray(int[] coords) {
        if (coords == null || coords.length != 2)
            throw new InvalidRequestException(ExceptionCodeEnum.PATCHES_INVALID);
        return new Position(coords[0], coords[1]);
    }
    public Position moveBy(Step step) {
        return new Position(this.column + step.difColumn(), this.row + step.difRow());
    }

    public List<Integer> toIntList() {
        return List.of(column, row);
    }
}
