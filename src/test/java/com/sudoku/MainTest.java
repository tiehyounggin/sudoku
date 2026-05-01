package com.sudoku;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testValidateUserInput() {
        List<BoardPosition> boardPositions = new ArrayList<>();
        boardPositions.add(new BoardPosition("5", 0, 0, "", false));

        String[] validInput = {"A1", "5"};
        assertTrue(Main.validateUserInput(validInput, boardPositions));

        String[] invalidCommand = {"invalid", "command"};
        assertFalse(Main.validateUserInput(invalidCommand, boardPositions));

        String[] prefilledInput = {"A2", "6"};
        assertFalse(Main.validateUserInput(prefilledInput, boardPositions));
    }

}
