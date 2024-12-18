package com.jsandis.robot.model;

public class Room {
    private final int width;
    private final int depth;

    public Room(int width, int depth) {
        this.width = width;
        this.depth = depth;
    }

    public boolean isOutsideBounds(Position position) {
        return position.x() < 0 || position.x() >= this.width ||
                position.y() < 0 || position.y() >= this.depth;
    }
}

