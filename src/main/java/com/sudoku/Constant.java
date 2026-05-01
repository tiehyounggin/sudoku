package com.sudoku;

import java.util.Map;

public class Constant {
    public static final String[] ALLOWED_SIZE_1_COMMAND = {"hint", "check", "show"};
    public static final Map<Integer, String> ROW_ALPHABET = Map.of(1, "A", 2, "B", 3, "C", 4,
            "D", 5, "E", 6, "F", 7, "G", 8, "H", 9, "I");
    public static final Map<String, Integer> ROW_ALPHABET_REVERSE = Map.of("A", 0, "B", 1, "C", 2,
            "D", 3, "E", 4, "F", 5, "G", 6, "H", 7, "I", 8);
}
