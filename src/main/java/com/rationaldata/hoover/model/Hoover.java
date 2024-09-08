package com.rationaldata.hoover.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hoover {
    private Position position;
    private PositionValidator positionValidator;
    private List<HooverPositionListener> positionListeners = new ArrayList<>();

    public Hoover(Position position, PositionValidator positionValidator) {
        this.position = position;
        this.positionValidator = positionValidator;
    }

    public Position getPosition() {
        return position;
    }

    public void addPositionListener(HooverPositionListener listener) {
        this.positionListeners.add(listener);
    }

    private void moveBy(Direction direction) {
        Position newPosition = this.position.moveBy(direction.getStep());
        boolean isValid = true;
        if (positionValidator != null)
            isValid = positionValidator.isPositionValid(newPosition);
        if (isValid)
            setPosition(newPosition);
    }

    public void executeMoveInstructions(String instructions) {
        setPosition(position);
        for (char c : instructions.toCharArray()) {
            Direction direction = Direction.fromChar(c);
            if (direction != null) {
                moveBy(direction);
            }
        }
    }

    private void setPosition(Position position) {
        this.position = position;
        for (var listener: positionListeners)
            listener.onPositionChanged(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hoover hoover = (Hoover) o;
        return Objects.equals(position, hoover.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
