# Robot Controller Assignment

## Overview

This is a programming assignment for implementing a simple robot controller in Java, 
and I have chosen to do that using Spring Boot.

The robot is able to move within a room's bounds, respond to commands, and provide status reports.

## Prerequisites

Before running the application or tests, ensure that you have the following installed:

- **Java**: JDK 17 or higher
- **Maven**: optional, included with wrapper
- **IDE**: IntelliJ IDEA or any other Java IDE (optional but recommended)

## Setup

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/johanna-sandberg/robot-controller.git
   ```

2. Go to the project directory from your workspace directory:
   ```bash
   cd robot-controller
   ```

## Run the Application

You can run the application in two ways:

### Option 1: Run via Terminal (using Maven)

- Open a terminal window and navigate to the project directory.
- Run the following command to start the application:
  ```bash
  ./mvnw spring-boot:run
  ```

### Option 2: Run in IDE (e.g., IntelliJ)

- Open the project in your IDE.
- Run the main class `RobotControllerApplication` from your IDE.

This will start the application, and the robot will be ready to process commands.

## Test the Application

To run the tests for the project you can use one of these options:

### Option 1: Run via Terminal (using Maven)

```bash
mvn test
```

### Option 2: Run in IDE (e.g., IntelliJ)

- Open the project in your IDE.
- Run the test classes from your IDE.

This will execute all unit tests and ensure that the application behaves as expected.

## Use the application

Once the application is running, you can interact with the robot by sending commands through the configured input
method (e.g., through an endpoint, console input, etc.).
Some instructions are copied from the assignment that this application is based on:

Input is first two numbers, which tells the robot how big the room is:

```
5 7
```

Which means that the room is 5 fields wide and is 7 fields deep.

The size of the room follows two digits and one letter indicating the starting
position of the robot and its orientation in space. For example:

```
3 3 N
```

Which means that the robot is in field (3, 3) and faces north.

Then you input commands to control the robot:

- `L` for turning left
- `R` for turning right
- `F` for moving forward
  Like this:

```
LFFRFRFRFF
```

If the robot walks outside the room bounds the application will exit with an error code.
More about error codes in section [Error Handling](#error-handling)

After processing the commands without errors the robot will generate a report of its final position and direction, and exit with exit code 0.

Examples with expected results:

```
5 5
1 2 N
RFRFFRFRF
Report: 1 3 N
```

```
5 5
0 0 E
RFLFFLRF
Report: 3 1 E
```

## Error Handling

Error messages are logged to the application logs. If the program exits with an error, check the logs for details.

### Exit/Error Codes

- `0`: Success
- `1`: Invalid input, e.g. invalid room dimensions, invalid starting position or direction
- `2`: Robot moved out of room bounds
- `3`: Unexpected error


## Project Structure

```
src
├── main
│   └── java
│       └── com
│           └── jsandis
│               └── robot
│                   ├── RobotControllerApplication.java
│                   ├── model
│                   │   ├── Direction.java
│                   │   ├── Position.java
│                   │   ├── Robot.java
│                   │   └── Room.java
│                   └── service
│                       └── RobotService.java
├── test
│   └── java
│       └── com
│           └── jsandis
│               └── robot
│                   ├── model
│                   │   ├── RobotTest.java
│                   │   └── RoomTest.java
│                   └── service
│                       └── RobotServiceTest.java
```

- **`/src/main/java/com/jsandis/robot/`**: Contains the main application logic and classes:
    - **`RobotControllerApplication.java`**: The entry point of the Spring Boot application.
    - **`model/`**: Contains all the model classes such as `Direction`, `Position`, `Robot`, and `Room`.
    - **`service/`**: Contains service class `RobotService`, which has logic for handling robot's moving, turning,
      and generating reports.

- **`/src/test/java/com/jsandis/robot/`**: Contains the test classes:
    - **`model/`**: Tests for the model classes: `RobotTest` and `RoomTest`.
    - **`service/`**: Tests for service class `RobotServiceTest` to validate the functionality of the `RobotService`.
