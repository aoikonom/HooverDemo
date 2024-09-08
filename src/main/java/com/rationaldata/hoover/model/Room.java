package com.rationaldata.hoover.model;

import java.util.Set;

public class Room implements PositionValidator {
    private int rows;
    private int columns;

    public Room(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public boolean isPositionValid(Position position) {
        return position.row() >= 0 && position.row() < rows && position.column() >= 0 && position.column() < columns;
    }
}
