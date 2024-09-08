package com.rationaldata.hoover.model;

import java.util.List;

public record HooverMoveInstructionsRequest(List<Integer> roomSize, List<Integer> coords, List<int[]> patches, String instructions) {}