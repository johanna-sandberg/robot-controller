package com.jsandis.robot.model;

public record Room(int width, int depth) {

    public boolean isOutsideBounds(Position position) {
        return position.x() < 0 || position.x() >= this.width ||
                position.y() < 0 || position.y() >= this.depth;
    }
}
