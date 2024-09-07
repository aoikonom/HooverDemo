package com.rationaldata.hoover.model;

public enum Direction {
    North('N', new Step(1, 0)),
    South('S', new Step(-1, 0)),
    East('E', new Step(0, 1)),
    West('W', new Step(0, -1));
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
        return null;
    }

    public char getName() {
        return name;
    }

    public Step getStep() {
        return step;
    }
}
