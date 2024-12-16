package com.jsandis.robot.model;

public class Room {
    private final int width;
    private final int height;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isWithinBounds(Position position) {
        return position.x() >= 0 && position.x() < width &&
                position.y() >= 0 && position.y() < height;
    }
}

