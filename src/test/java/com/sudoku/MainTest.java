package com.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Board board;
    private List<BoardPosition> boardPositions;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        board = new Board();
        boardPositions = new ArrayList<>();
    }

    @Test
    void testValidateUserInput() {
        boardPositions.add(new BoardPosition("5", 0, 0, "", false));

        String[] validInput = {"A1", "5"};
        assertTrue(Main.validateUserInput(validInput, boardPositions));

        String[] validInputClear = {"A1", "clear"};
        assertTrue(Main.validateUserInput(validInputClear, boardPositions));

        String[] invalidCommand = {"invalid", "command"};
        assertFalse(Main.validateUserInput(invalidCommand, boardPositions));

        String[] prefilledInput = {"A2", "6"};
        assertFalse(Main.validateUserInput(prefilledInput, boardPositions));
    }

    @Test void testValidateUserInputNumberNegative(){
        String[] validInput = {"A2", "-4"};
        assertFalse(Main.validateUserInput(validInput, boardPositions));
    }

    @Test
    void testVlidateUserInputCommandSize3(){
        String[] invalidCommand2 = {"invalid", "command", "hello"};
        assertFalse(Main.validateUserInput(invalidCommand2, boardPositions));
    }

    @Test
    void testValidateUserInputPreFilledTrue(){
        board.setGridBoard(2, 2, "7");

        String[] prefilledInput = {"C3", "7"};
        Main.validateUserInput(prefilledInput, boardPositions);
        assertTrue(outContent.toString().contains("Invalid move. C3 is pre-filled."));
    }

}
