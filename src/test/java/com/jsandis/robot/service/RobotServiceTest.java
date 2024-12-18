package com.jsandis.robot.service;

import com.jsandis.robot.model.Position;
import com.jsandis.robot.model.enums.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotServiceTest {

    private RobotService robotService;

    @BeforeEach
    void setUp() {
        robotService = new RobotService();
    }

    @Test
    void testInitializeWithStartPositionOutOfBounds() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(6, 6);
        Direction north = Direction.N;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> robotService.initialize(roomWidth, roomDepth, startPosition, north)
        );
        assertEquals("Start position is outside room bounds: Position[x=6, y=6]", exception.getMessage());
    }

    @Test
    void testInitializeWithOutOfBoundsPositionInLargeRoom() {
        int roomWidth = 1000;
        int roomDepth = 1000;
        Position startPosition = new Position(1001, 500);
        Direction startDirection = Direction.N;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> robotService.initialize(roomWidth, roomDepth, startPosition, startDirection)
        );
        assertEquals("Start position is outside room bounds: " + startPosition, exception.getMessage());
    }

    @Test
    void testProcessCommandsInvalidCommand() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(1, 2);
        Direction north = Direction.N;
        robotService.initialize(roomWidth, roomDepth, startPosition, north);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> robotService.processCommands("LFX")
        );
        assertTrue(exception.getMessage().contains("Commands can only contain 'L', 'R', or 'F'. Commands entered: LFX"));
    }

    @Test
    void testProcessCommandsMoveOutOfBounds() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(0, 0);
        Direction north = Direction.N;
        robotService.initialize(roomWidth, roomDepth, startPosition, north);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> robotService.processCommands("F")
        );
        assertTrue(exception.getMessage().contains("Robot can't move outside of room bounds. Current position: Position[x=0, y=-1], Room dimensions: 5x5"));
    }

    @Test
    void testProcessCommandsValidCommands() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(1, 2);
        Direction north = Direction.N;
        robotService.initialize(roomWidth, roomDepth, startPosition, north);

        String result = robotService.processCommands("RFRFFRFRF");

        assertEquals("Report: 1 3 N", result);
    }

    @Test
    void testProcessCommandsComplexSequence() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(0, 0);
        Direction east = Direction.E;
        robotService.initialize(roomWidth, roomDepth, startPosition, east);

        String result = robotService.processCommands("RFLFFLRF");

        assertEquals("Report: 3 1 E", result);
    }

    @Test
    void testRobotMoveInLargeRoom() {
        int roomWidth = 1000;
        int roomDepth = 1000;
        Position startPosition = new Position(500, 500);
        Direction startDirection = Direction.N;

        robotService.initialize(roomWidth, roomDepth, startPosition, startDirection);

        String result = robotService.processCommands("F");

        assertEquals("Report: 500 499 N", result);
    }

    @Test
    void testRobotMoveOutOfBoundsInLargeRoom() {
        int roomWidth = 1000;
        int roomDepth = 1000;
        Position startPosition = new Position(0, 0);
        Direction startDirection = Direction.N;

        robotService.initialize(roomWidth, roomDepth, startPosition, startDirection);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> robotService.processCommands("F")
        );
        assertTrue(exception.getMessage().contains("Robot can't move outside of room bounds"));
    }
}
