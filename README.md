# Sudoku

A simple console-based Sudoku game written in Java.

## Gameplay

The game generates a random Sudoku puzzle and displays it on the console. You can interact with the game by entering commands to fill in numbers, clear cells, get hints, and check your progress.

### Commands

-   **Fill a cell**: To fill a cell, enter the row letter, column number, and the value you want to place.

    `A3 4`

-   **Clear a cell**: To clear a cell, enter the row letter, column number, and the word "clear".

    `C5 clear`

-   **Get a hint**: To get a hint, type `hint`. The game will reveal the correct value for an empty cell.

-   **Check your input**: To check if your last input violates any Sudoku rules, type `check`.

-   **Quit**: To quit the game, type `quit`.


## Building and Running

This project uses Maven for dependency management and building.

### Prerequisites

-   Java 17 or higher
-   Maven

### Building the Project

1.  Clone the repository:

    `git clone <repository-url>`

2.  Navigate to the project directory:

    `cd SudokuGame`

3.  Build the project using Maven:

    `mvn clean install`


### Running the Game

After building the project, you can run the game from the command line:

`java -jar target/SudokuGame-1.0-SNAPSHOT-shaded.jar`
