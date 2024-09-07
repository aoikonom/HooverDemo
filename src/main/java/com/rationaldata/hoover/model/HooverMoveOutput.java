package com.rationaldata.hoover.model;

import java.util.List;

public class HooverMoveOutput {
    private List<Integer> coords;
    private int patches;

    public HooverMoveOutput(List<Integer> coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public List<Integer> getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }
}
