package com.jsandis.robot.model;

import com.jsandis.robot.model.enums.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    private Robot robot;

    @BeforeEach
    void setUp() {
        robot = new Robot(new Position(2, 2), Direction.N);
    }

    @Test
    void testInitPositionAndDirection() {
        assertEquals(new Position(2, 2), robot.getPosition());
        assertEquals(Direction.N, robot.getDirection());
    }

    @Test
    void testTurnLeft() {
        robot.turnLeft();
        assertEquals(Direction.W, robot.getDirection());
    }

    @Test
    void testTurnRight() {
        robot.turnRight();
        assertEquals(Direction.E, robot.getDirection());
    }

    @Test
    void testMoveForwardDirectionStillNorth() {
        robot.moveForward();
        assertEquals(new Position(2, 1), robot.getPosition());
        assertEquals(Direction.N, robot.getDirection());
    }

    @Test
    void testMoveForwardDirectionEast() {
        robot.setDirection(Direction.E);
        robot.moveForward();
        assertEquals(new Position(3, 2), robot.getPosition());
        assertEquals(Direction.E, robot.getDirection());
    }

    @Test
    void testMoveForwardDirectionSouth() {
        robot.setDirection(Direction.S);
        robot.moveForward();
        assertEquals(new Position(2, 3), robot.getPosition());
        assertEquals(Direction.S, robot.getDirection());
    }

    @Test
    void testMoveForwardDirectionWest() {
        robot.setDirection(Direction.W);
        robot.moveForward();
        assertEquals(new Position(1, 2), robot.getPosition());
        assertEquals(Direction.W, robot.getDirection());
    }

    @Test
    void testSetPosition() {
        Position newPosition = new Position(5, 5);
        robot.setPosition(newPosition);
        assertEquals(newPosition, robot.getPosition());
    }

    @Test
    void testSetDirection() {
        robot.setDirection(Direction.E);
        assertEquals(Direction.E, robot.getDirection());
    }
}
