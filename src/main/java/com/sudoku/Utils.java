package com.sudoku;

public class Utils {
    public static String convertRowColToAlphabet(int row, int col){
        return Constant.ROW_ALPHABET_REVERSE.entrySet().stream()
                .filter(entry -> entry.getValue() == row)
                .findFirst().get().getKey() + col;
    }

    public static int convertPositionAlphabetToRowInt(String alphabetWithNum){
        return Constant.ROW_ALPHABET_REVERSE.get(alphabetWithNum.substring(0, 1));
    }

    public static int convertPositionNumberToColInt(String alphabetWithNum){
        return Integer.parseInt(alphabetWithNum.substring(1, 2)) - 1;
    }
}
