package com.rationaldata.hoover.model;

import com.rationaldata.hoover.exceptions.ExceptionCodeEnum;
import com.rationaldata.hoover.exceptions.InvalidRequestException;

public enum Direction {
    North('N', new Step(0, 1)),
    South('S', new Step(0, -1)),
    East('E', new Step(1, 0)),
    West('W', new Step(-1, 0));
    private char name;
    private Step step;

    Direction(char name, Step step) {
        this.name = name;
        this.step = step;
    }

    static Direction fromChar(char c) {
        for (Direction dir : values())
            if (dir.name == c)
                return dir;
        throw new InvalidRequestException(ExceptionCodeEnum.UNKNWON_INSTRUCTION);
    }

    public char getName() {
        return name;
    }

    public Step getStep() {
        return step;
    }
}
