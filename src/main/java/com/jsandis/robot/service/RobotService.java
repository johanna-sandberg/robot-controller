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

        //Loop through commands and handle each case
        //Don't forget error handling including out of bounds

        //return result

        return null;
    }
}
