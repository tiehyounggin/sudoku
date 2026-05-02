package com.sudoku;

import java.util.*;

public class Board {
    private String[][] gridBoard = new String[9][9];

    public Board(){

    }

    //constructor for copy
    public Board(Board board2){
        this.gridBoard = board2.gridBoard;
    }

    public String getGridboard(int row, int col){
        return this.gridBoard[row][col];
    }

    public void setGridBoard(int row, int col, String val){
        this.gridBoard[row][col] = val;
    }

    public void initialize(){

        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        populateBoard(numList, 0, 0);
    }

    public void printBoard(){
        int xCounter = 0;


        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){

                if(i == 0){
                    if(j != 0){
                        System.out.print(xCounter + " ");
                    }else{
                        System.out.print("  ");
                    }
                }else if(j == 0){
                    System.out.print(Constant.ROW_ALPHABET.get(i) + " ");
                }else{
                    System.out.print(gridBoard[i - 1][j - 1] + " ");
                }

                xCounter++;
            }
            System.out.println();
        }
    }

    public List<BoardPosition> hideBoard(int hideNum){

        Set<Integer> uniHiddenPos = new HashSet<>();

        //generator unique
        while(uniHiddenPos.size() < hideNum){
            int randNum = (int) (Math.random() * 88) + 1;

            if(randNum % 10 != 9){
                uniHiddenPos.add(randNum);
            }
        }

        //convert
        List<BoardPosition> boardPosList = uniHiddenPos.stream()
                .map(pos -> {
                    int row;
                    int col;

                    if(pos < 10){
                        row = 0;
                        col = pos;
                    }else{
                        row = pos / 10;
                        col = pos % 10;
                    }

                    BoardPosition boardPosition = new BoardPosition(gridBoard[row][col], row, col, "", false);
                    gridBoard[row][col] = "_";

                    return boardPosition;
                }).toList();

        Collections.shuffle(new ArrayList<>(boardPosList));

        return boardPosList;
    }

    //for user
    public boolean checkValidator(int row, int col, int number){
        int rowOccurence = 0;
        int colOccurence = 0;
        int subGridOccurrence = 0;

        //row check
        for(int i = 0; i < 9; i++){
            if(String.valueOf(number).equals(gridBoard[row][i])){
                rowOccurence++;
            }
        }

        if(rowOccurence > 1){
            System.out.println("Number " + number + " already exists in Row " + row + 1);
            return false;
        }

        //col check
        for(int i = 0; i < 9; i++){
            if(String.valueOf(number).equals(gridBoard[i][col])){
                colOccurence++;
            }
        }

        if(colOccurence > 1){
            System.out.println("Number " + number + " already exists in Column " + col + 1);
            return false;
        }

        //small grid check
        int rowStart = row - (row % 3);
        int colStart = col - (col % 3);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(String.valueOf(number).equals(gridBoard[rowStart + i][colStart + j])){
                    subGridOccurrence++;
                }
            }
        }

        if(subGridOccurrence > 1) {
            System.out.println("Number " + number + " already exists in the same 3×3 subgrid");
            return false;
        }

        return true;
    }

    private boolean populateBoard(List<Integer> numList, int row, int col){

        if (row == 9){
            return true;
        }

        Collections.shuffle(numList);

        int nextRow;
        if(col == 8){
            nextRow = row + 1;
        }else{
            nextRow = row;
        }

        int nextCol;
        if(col == 8){
            nextCol = 0;
        }else{
            nextCol = col + 1;
        }

        for (int eachNum: numList) {
            if (positionValidator(row, col, eachNum)) {
                gridBoard[row][col] = String.valueOf(eachNum);

                if (populateBoard(numList, nextRow, nextCol)) {
                    return true;
                }

                gridBoard[row][col] = String.valueOf(0);
            }
        }

        return false;
    }

    private boolean positionValidator(int row, int col, int number){

        //row check
        for(int i = 0; i < 9; i++){
            if(String.valueOf(number).equals(gridBoard[row][i])){
                return false;
            }
        }

        //col check
        for(int i = 0; i < 9; i++){
            if(String.valueOf(number).equals(gridBoard[i][col])){
                return false;
            }
        }

        //small grid check
        int rowStart = row - (row % 3);
        int colStart = col - (col % 3);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(String.valueOf(number).equals(gridBoard[rowStart + i][colStart + j])){
                    return false;
                }
            }
        }

        return true;
    }
}
