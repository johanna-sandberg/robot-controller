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
    void testInitializeValidRoomAndRobot() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(1, 2);
        Direction north = Direction.N;
        robotService.initialize(roomWidth, roomDepth, startPosition, north);

        assertTrue(true);
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
    void testProcessCommandsInvalidCommand() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(1, 2);
        Direction north = Direction.N;
        robotService.initialize(roomWidth, roomDepth, startPosition, north);

        String result = robotService.processCommands("LFX");

        assertTrue(result.contains("Error: Command is invalid: X"));
    }

    @Test
    void testProcessCommandsMoveOutOfBounds() {
        int roomWidth = 5;
        int roomDepth = 5;
        Position startPosition = new Position(0, 0);
        Direction north = Direction.N;
        robotService.initialize(roomWidth, roomDepth, startPosition, north);

        String result = robotService.processCommands("F");

        assertTrue(result.contains("Error: Robot can't move outside of room bounds to position: 0 -1"));
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
}
