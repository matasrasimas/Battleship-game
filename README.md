# Battleship game

This project is a representation of battleship game, which is built with Spring Boot (Java) for the backend and React (JavaScript) for the frontend. The backend code is located in the `backend` folder, and the frontend code is located in the `frontend` folder.

## Prerequisites

Before you begin, ensure you have the following prerequisites installed on your machine:

- Java Development Kit (JDK) 8 or higher
- Node.js and npm (Node Package Manager)

Optionally:
- Apache Maven (for building the Spring Boot application)

## Getting Started

Follow the steps below to set up and run the project locally.

### Backend (Spring Boot)

1. By using Java IDE, which supports Maven (preferably Intellij), open the project, which is stored in directory `backend`.
2. Run the project by clicking 'run' button, which is located on the top of IDE (or by pressing `Shift+F10` on your keyboard).
3. The backend server will start at http://localhost:8080.

Alternatively, if you have installed Maven on your machine, then you can follow these steps to run backend:
1. By using `cd` command, navigate to the root of backend, which is located in `backend` folder.
2. Build the Spring Boot application using the following command: `mvn spring-boot:run`
3. The backend server will start at `http://localhost:8080`.


### Frontend (React)

1. By using `cd` command, navigate to the root frontend, which is located in `front` folder.
2. Install the dependencies by running `npm install`
3. Start the development server by running `npm run dev`
4. The terminal, in which you ran the command, will display the port, on which the frontend is running (most likely, it's `http://localhost:5173`)

Note: make sure you launch the project on `http://localhost:5173` instead of `http://127.0.0.1:5173/` to avoid any unexpected behavior!

## Contributing

Feel free to contribute to this project by opening issues or creating pull requests.
