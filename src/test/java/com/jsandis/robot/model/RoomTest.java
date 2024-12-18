package com.jsandis.robot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomTest {

    @Test
    void testPositionWithinBounds() {
        Room room = new Room(5, 5);

        Position position = new Position(2, 3);
        assertFalse(room.isOutsideBounds(position));
    }

    @Test
    void testPositionCloseToBounds() {
        Room room = new Room(5, 5);

        Position position = new Position(4, 4);
        assertFalse(room.isOutsideBounds(position));
    }

    @Test
    void testPositionOutsideBounds() {
        Room room = new Room(5, 5);

        Position position = new Position(5, 5);
        assertTrue(room.isOutsideBounds(position));
    }

    @Test
    void testNegativePosition() {
        Room room = new Room(5, 5);

        Position position = new Position(-1, -1);
        assertTrue(room.isOutsideBounds(position));
    }

    @Test
    void testPositionPartiallyOutsideBounds() {
        Room room = new Room(5, 5);

        Position positionXOut = new Position(5, 2);
        Position positionYOut = new Position(2, 5);

        assertTrue(room.isOutsideBounds(positionXOut));
        assertTrue(room.isOutsideBounds(positionYOut));
    }
}
