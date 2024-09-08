package com.rationaldata.hoover.model;

import java.util.Set;

public class Dirt implements HooverPositionListener {
    private Set<Position> dirt;
    private int initialDirt = 0;

    public Dirt(Set<Position> dirt) {
        this.dirt = dirt;
        this.initialDirt = dirt.size();
    }

    @Override
    public void onPositionChanged(Position position) {
        if (dirt.contains(position))
            dirt.remove(position);
    }

    public int getCleanedDirt() {
        return initialDirt - dirt.size();
    }

}
