package com.sudoku;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private User user;
    private Board board;
    private List<BoardPosition> boardPositions;
    private CircularFifoQueue<String> commandHistory;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        user = new User();
        board = new Board();
        boardPositions = new ArrayList<>();
        commandHistory = new CircularFifoQueue<>(5);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testPositionChange() {
        boardPositions.add(new BoardPosition("5", 0, 0, "", false));
        boolean isSolved = user.positionChange(board, boardPositions, "A1", "5");

        assertTrue(outContent.toString().contains("Move accepted."));
        Assertions.assertEquals("5", board.getGridboard(0, 0));
        assertTrue(isSolved);
    }

    @Test
    void testCommandHint() {
        boardPositions.add(new BoardPosition("3", 1, 1, "", false));
        user.command(board, "hint", boardPositions, commandHistory);
        assertTrue(outContent.toString().contains("Hint: Cell B2 = 3"));
    }

    @Test
    void testCommandCheck() {
        commandHistory.add("A1 5");
        boardPositions.add(new BoardPosition("5", 0, 0, "5", true));
        board.setGridBoard(0, 0, "5");
        user.command(board, "check", boardPositions, commandHistory);
        assertTrue(outContent.toString().contains("No rule violations detected."));
    }

    @Test
    void testCommandShow() {
        board.initialize();
        user.command(board, "show", boardPositions, commandHistory);
        String output = outContent.toString();
        assertTrue(output.contains("A"));
        assertTrue(output.contains("1"));
    }
}
