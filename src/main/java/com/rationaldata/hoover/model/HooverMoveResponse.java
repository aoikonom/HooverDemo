package com.rationaldata.hoover.model;

import java.util.List;

public record HooverMoveResponse(List<Integer> coords, int patches) {}