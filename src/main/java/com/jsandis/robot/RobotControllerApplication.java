package com.jsandis.robot;

import com.jsandis.robot.model.Position;
import com.jsandis.robot.model.enums.Direction;
import com.jsandis.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RobotControllerApplication implements CommandLineRunner {

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

        System.out.print("Enter room width and depth (E.g. '5 5'): ");
        String[] roomDimensions = scanner.nextLine().split(" ");
        //TODO: Break out and handle invalid room parameters
        int roomWidth = Integer.parseInt(roomDimensions[0]);
        int roomDepth = Integer.parseInt(roomDimensions[1]);

        System.out.print("Enter initial position x y and direction (E.g. '1 2 N'): ");
        String[] initialPosition = scanner.nextLine().split(" ");
        //TODO: Break out and handle invalid start position & direction parameters
        int startX = Integer.parseInt(initialPosition[0]);
        int startY = Integer.parseInt(initialPosition[1]);
        Position startPosition = new Position(startX, startY);
        Direction startDirection = Direction.valueOf(initialPosition[2]);

        System.out.print("Enter commands (L Turn left, R Turn right, F Walk forward'): ");
        String commands = scanner.nextLine();
        try {
            robotService.initialize(roomWidth, roomDepth, startPosition, startDirection);
            String result = robotService.processCommands(commands);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
