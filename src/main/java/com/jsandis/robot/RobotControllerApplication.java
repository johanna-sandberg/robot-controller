package com.jsandis.robot;

import com.jsandis.robot.model.Position;
import com.jsandis.robot.model.enums.Direction;
import com.jsandis.robot.service.RobotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RobotControllerApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RobotControllerApplication.class);

    private final RobotService robotService;

    @Autowired
    public RobotControllerApplication(RobotService robotService) {
        this.robotService = robotService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RobotControllerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter room width and depth (E.g. '5 5'): ");
            String[] roomDimensions = getRoomDimensions(scanner);
            int roomWidth = Integer.parseInt(roomDimensions[0]);
            int roomDepth = Integer.parseInt(roomDimensions[1]);

            System.out.print("Enter initial position x y and direction (E.g. '1 2 N'): ");
            String[] initialPosition = getInitialPositionAndDirection(scanner);
            Position startPosition = getStartPosition(initialPosition);
            Direction startDirection = Direction.valueOf(initialPosition[2]);

            System.out.print("Enter commands (L Turn left, R Turn right, F Walk forward'): ");
            String commands = scanner.nextLine();

            robotService.initialize(roomWidth, roomDepth, startPosition, startDirection);

            String result = robotService.processCommands(commands);
            System.out.println(result);

            System.exit(0);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid input: {}", e.getMessage(), e);
            System.exit(1);
        } catch (IllegalStateException e) {
            logger.error("Invalid state: {}", e.getMessage(), e);
            System.exit(2);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage(), e);
            System.exit(3);
        }
    }

    private static String[] getRoomDimensions(Scanner scanner) {
        String[] roomDimensions = scanner.nextLine().split(" ");
        if (roomDimensions.length != 2 || !roomDimensions[0].matches("\\d+") || !roomDimensions[1].matches("\\d+")) {
            throw new IllegalArgumentException("Room dimensions must be two positive integers.");
        }
        return roomDimensions;
    }

    private static String[] getInitialPositionAndDirection(Scanner scanner) {
        String[] initialPosition = scanner.nextLine().split(" ");
        if (initialPosition.length != 3 ||
                !initialPosition[0].matches("\\d+") ||
                !initialPosition[1].matches("\\d+") ||
                !initialPosition[2].matches("[NSEW]")) {
            throw new IllegalArgumentException("Initial position must contain two positive integers and a valid direction (N, S, E, or W).");
        }
        return initialPosition;
    }

    private static Position getStartPosition(String[] initialPosition) {
        int startX = Integer.parseInt(initialPosition[0]);
        int startY = Integer.parseInt(initialPosition[1]);
        return new Position(startX, startY);
    }
}
