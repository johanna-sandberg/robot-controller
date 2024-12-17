package com.jsandis.robot.service;

import com.jsandis.robot.model.Position;
import com.jsandis.robot.model.Robot;
import com.jsandis.robot.model.Room;
import com.jsandis.robot.model.enums.Direction;

public class RobotService {

    private Room room;
    private Robot robot;

    public RobotService(int roomWidth, int roomHeight, Position startPosition, Direction startDirection) {
        this.room = new Room(roomWidth, roomHeight);
        this.robot = new Robot(startPosition, startDirection);
    }

    public String handleCommands(String commands) {
        System.out.println("Room: " + room);
        System.out.println("Robot: " + robot);
        System.out.println("Commands: " + commands);

        for(char command : commands.toCharArray()) {
            switch (command) {
                case 'L' -> robot.turnLeft();
                case 'R' -> robot.turnRight();
                case 'F' -> moveRobotForwardWithBoundsHandling();
                default -> throw new IllegalArgumentException("Command is invalid: " + command);
            }
        }
        return "Report: " + robot.getPosition().x() + " " + robot.getPosition().y() + " " + robot.getDirection().getShortName();
    }

    private void moveRobotForwardWithBoundsHandling() {
        Position newPosition = robot.moveForward();
        if (!room.isWithinBounds(newPosition)) {
            throw new IllegalStateException("Robot can't move outside of room bounds at position: " + newPosition.x() + " " + newPosition.y());
        }
    }
}
