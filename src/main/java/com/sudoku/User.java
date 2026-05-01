package com.sudoku;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    public boolean positionChange(Board board, List<BoardPosition> boardPositions, String rolCol, String inputVal){

        int row = Utils.convertPositionAlphabetToRowInt(rolCol);
        int col = Utils.convertPositionNumberToColInt(rolCol);

        board.setGridBoard(row, col, "clear".equalsIgnoreCase(inputVal) ? "_" : inputVal);

        boardPositions.stream().filter(pos -> pos.getRow() == row && pos.getCol() == col)
                .findFirst()
                .ifPresent(pos -> {
                    System.out.println("\nMove accepted.\n");
                    pos.setPosUserValue("clear".equalsIgnoreCase(inputVal) ? "_" : inputVal);
                    pos.setFilled("clear".equalsIgnoreCase(inputVal) ? false : true);
                });

        if(!"clear".equalsIgnoreCase(inputVal)){

            return boardPositions.stream().allMatch(pos -> pos.getPosUserValue().equalsIgnoreCase(pos.getPosCorrectValue()));
        }

        return false;
    }

    public void command(Board board, String command, List<BoardPosition> boardPositions, CircularFifoQueue<String> commandHistory){

        if("hint".equalsIgnoreCase(command)){
            List<BoardPosition> boardPositionsToShuffle = new ArrayList<>(boardPositions);
            Collections.shuffle(boardPositionsToShuffle);

            boardPositionsToShuffle.stream().filter(pos -> !pos.isFilled())
                    .findFirst()
                    .ifPresent(pos -> {
                System.out.println("Hint: Cell " + Utils.convertRowColToAlphabet(pos.getRow(), pos.getCol() + 1) + " = " + pos.getPosCorrectValue());
            });
        }else if("check".equalsIgnoreCase(command)){
            if(!commandHistory.isEmpty()){
                String latestCommand = commandHistory.get(commandHistory.size() - 1);
                String[] latestCommandArr = latestCommand.split(" ");
                int row = Utils.convertPositionAlphabetToRowInt(latestCommandArr[0]);
                int col = Utils.convertPositionNumberToColInt(latestCommandArr[0]);

                boardPositions.stream().filter(pos -> pos.getRow() == row && pos.getCol() == col)
                        .findFirst()
                        .ifPresent(pos -> {
                            if(pos.isFilled()){
                                if(board.checkValidator(row, col, Integer.parseInt(latestCommandArr[1]))){
                                    System.out.println("No rule violations detected.");
                                }
                            }else{
                                System.out.println("Previous move has already been undone.");
                            }
                        });
            }else{
                System.out.println("History is empty");
            }

        }else if("show".equalsIgnoreCase(command)){
            board.printBoard();
        }
    }
}
