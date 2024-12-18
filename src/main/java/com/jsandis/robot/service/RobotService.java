package com.jsandis.robot.service;

import com.jsandis.robot.model.Position;
import com.jsandis.robot.model.Robot;
import com.jsandis.robot.model.Room;
import com.jsandis.robot.model.enums.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RobotService {

    private static final Logger logger = LoggerFactory.getLogger(RobotService.class);

    private Room room;
    private Robot robot;

    public RobotService() {
    }

    public void initialize(int roomWidth, int roomDepth, Position startPosition, Direction startDirection) {
        this.room = new Room(roomWidth, roomDepth);

        if (room.isOutsideBounds(startPosition)) {
            throw new IllegalArgumentException("Start position is outside room bounds: " + startPosition);
        }

        this.robot = new Robot(startPosition, startDirection);
        logger.info("Room initialized with width: {} and depth: {}", roomWidth, roomDepth);
        logger.info("Robot initialized with position: {} and direction: {}", startPosition, startDirection);
    }

    public String processCommands(String commands) {
        logger.info("Processing commands: {}", commands);

        for (char command : commands.toCharArray()) {
            try {
                handleCommand(command);
            } catch (Exception e) {
                logger.error("Error processing command {}: {}", command, e.getMessage());
                return "Error: " + e.getMessage();
            }
        }
        return "Report: " + this.robot.getPosition().x() + " " + this.robot.getPosition().y() + " "
                + this.robot.getDirection();
    }

    private void handleCommand(char command) {
        switch (command) {
            case 'L' -> this.robot.turnLeft();
            case 'R' -> this.robot.turnRight();
            case 'F' -> moveRobotForwardWithBoundsHandling();
            default -> throw new IllegalArgumentException("Command is invalid: " + command);
        }
        logger.info("New position and direction: {} {}", this.robot.getPosition(), this.robot.getDirection());
    }

    private void moveRobotForwardWithBoundsHandling() {
        this.robot.moveForward();

        Position newPosition = this.robot.getPosition();

        if (room.isOutsideBounds(newPosition)) {
            throw new IllegalStateException("Robot can't move outside of room bounds to position: "
                    + newPosition.x() + " " + newPosition.y());
        }
    }

}
