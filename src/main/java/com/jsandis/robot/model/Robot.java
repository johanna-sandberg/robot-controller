package com.jsandis.robot.model;

import com.jsandis.robot.model.enums.Direction;
import lombok.Getter;

@Getter
public class Robot {

    private final Position position;
    private Direction direction;

    public Robot(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public Position moveForward() {
        return switch (direction) {
            case NORTH -> new Position(position.x(), position.y() + 1);
            case EAST -> new Position(position.x() + 1, position.y());
            case SOUTH -> new Position(position.x(), position.y() - 1);
            case WEST -> new Position(position.x() - 1, position.y());
        };
    }
}
