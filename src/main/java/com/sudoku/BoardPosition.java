package com.sudoku;

public class BoardPosition {
    private int row;
    private int col;
    private String posUserValue;
    private final String posCorrectValue;
    private boolean isFilled;

    public BoardPosition(String posCorrectValue, int row, int col, String posUserValue, boolean isFilled) {
        this.posCorrectValue = posCorrectValue;
        this.row = row;
        this.col = col;
        this.posUserValue = posUserValue;
        this.isFilled = isFilled;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getPosUserValue() {
        return posUserValue;
    }

    public void setPosUserValue(String posUserValue) {
        this.posUserValue = posUserValue;
    }

    public String getPosCorrectValue() {
        return posCorrectValue;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }
}
