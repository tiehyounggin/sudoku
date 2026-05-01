package com.sudoku;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Board board = new Board();
        List<BoardPosition> hiddenPosList;
        CircularFifoQueue<String> commandHistory = new CircularFifoQueue<>(20);
        boolean isGameComplete = false;

//        sudoku.Board boardAns = new sudoku.Board(board);
        board.initialize();

        System.out.println("Welcome to Sudoku!");
        System.out.println("Here is your puzzle:");
        hiddenPosList = board.hideBoard(51);
        board.printBoard();

        Scanner scanner = new Scanner(System.in);

        String userInput = "";

        //A3 4, C5 clear, hint, check, quit
        while(!"quit".equalsIgnoreCase(userInput)){

            System.out.println("\nEnter command (e.g., A3 4, C5 clear, hint, check):");
            userInput = scanner.nextLine();

            String[] userInputArr = userInput.split(" ");

            if(!validateUserInput(userInputArr, hiddenPosList)){
                if(!"quit".equalsIgnoreCase(userInputArr[0])){
                    System.out.println("Please enter a valid command");
                }
            }else if(userInputArr.length == 2){
                if(!"clear".equalsIgnoreCase(userInputArr[1])){
                    commandHistory.add(userInputArr[0] + " " + userInputArr[1]);
                }

                isGameComplete = user.positionChange(board, hiddenPosList, userInputArr[0], userInputArr[1]);

                System.out.println("Current grid:");
                board.printBoard();
            }else if(userInputArr.length == 1){
                user.command(board, userInputArr[0], hiddenPosList, commandHistory);
            }

            if(isGameComplete){
                System.out.println("\nYou have successfully completed the Sudoku puzzle!");
                System.out.println("Press any key to play again...");
                scanner.nextLine();
                isGameComplete = false;

                board.initialize();
                hiddenPosList = board.hideBoard(51);
                board.printBoard();
            }
        }
//        boardAns.printBoard();
    }

    public static boolean validateUserInput(String[] userInputArr, List<BoardPosition> boardPositions){

        //checker for size 1 command and is allowed command
        if(userInputArr.length == 1){
            if(Arrays.stream(Constant.ALLOWED_SIZE_1_COMMAND).anyMatch(command -> command.equalsIgnoreCase(userInputArr[0]))){
                return true;
            }
        }

        //checker for size 2 command and pattern match
        //include check for pre filled positions
        if(userInputArr.length == 2){
            if(userInputArr[0].matches("[A-I][0-9]") && (userInputArr[1].matches("[0-9]") || "clear".equalsIgnoreCase(userInputArr[1]))){
                int row = Utils.convertPositionAlphabetToRowInt(userInputArr[0]);
                int col = Utils.convertPositionNumberToColInt(userInputArr[0]);

                if(boardPositions.stream().noneMatch(pos -> pos.getRow() == row && pos.getCol() == col)){
                    System.out.println("Invalid move. " + userInputArr[0] + " is pre-filled.");
                    return false;
                }

                return true;
            }
        }

        return false;
    }
}
