package com.rationaldata.hoover.model;

import java.util.Set;

public class Room implements PositionValidator, HooverPositionListener{
    private int rows;
    private int columns;
    private Set<Position> dirt;
    private int initialDirt = 0;

    public Room(int rows, int columns, Set<Position> dirt) {
        this.rows = rows;
        this.columns = columns;
        this.dirt = dirt;
        this.initialDirt = dirt.size();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public void onPositionChanged(Position position) {
        if (dirt.contains(position))
            dirt.remove(position);
    }

    @Override
    public boolean isPositionValid(Position position) {
        return position.row() >= 0 && position.row() < rows && position.column() >= 0 && position.column() < columns;
    }

    public int getCleanedDirt() {
        return dirt.size() - initialDirt;
    }
}
