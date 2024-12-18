package com.jsandis.robot.model;

import com.jsandis.robot.model.enums.Direction;

public class Robot {

    private Position position;
    private Direction direction;

    public Robot(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void turnLeft() {
        this.direction = direction.left();
    }

    public void turnRight() {
        this.direction = direction.right();
    }

    public void moveForward() {
        int newX = this.position.x();
        int newY = this.position.y();

        switch (this.direction) {
            case N -> newY -= 1;
            case E -> newX += 1;
            case S -> newY += 1;
            case W -> newX -= 1;
        }
        this.position = new Position(newX, newY);
    }
}
