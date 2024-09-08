package com.rationaldata.hoover.model;

import java.util.List;

public class HooverMoveResponse {
    private List<Integer> coords;
    private int patches;

    public HooverMoveResponse(List<Integer> coords, int patches) {
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
