package com.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testBoardInitialization() {
        Board board = new Board();
        assertNotNull(board, "sudoku.Board should be instantiated");
    }

    @Test
    void testCheckValidator() {
        Board board = new Board();

        board.setGridBoard(0, 0, "5");
        board.setGridBoard(0, 1, "3");
        board.setGridBoard(1, 0, "6");
        board.setGridBoard(4, 4, "8");
        board.setGridBoard(1, 1, "9");

        Assertions.assertTrue(board.checkValidator(8, 8, 7));

        board.setGridBoard(0, 2, "3");
        Assertions.assertFalse(board.checkValidator(0, 2, 3));
        board.setGridBoard(0, 2, "_");

        board.setGridBoard(2, 0, "6");
        Assertions.assertFalse(board.checkValidator(2, 0, 6));
        board.setGridBoard(2, 0, "_");


        board.setGridBoard(2, 2, "9");
        Assertions.assertFalse(board.checkValidator(2, 2, 9));
    }
}
