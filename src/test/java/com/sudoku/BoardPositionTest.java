package com.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardPositionTest {

    @Test
    void testBoardPositionConstructor() {
        BoardPosition pos = new BoardPosition("5", 1, 2, "5", true);
        assertEquals("5", pos.getPosCorrectValue());
        assertEquals(1, pos.getRow());
        assertEquals(2, pos.getCol());
        assertEquals("5", pos.getPosUserValue());
        assertTrue(pos.isFilled());
    }

    @Test
    void testSetters() {
        BoardPosition pos = new BoardPosition("1", 0, 0, "", false);
        pos.setRow(3);
        pos.setCol(4);
        pos.setPosUserValue("9");
        pos.setFilled(true);

        assertEquals(3, pos.getRow());
        assertEquals(4, pos.getCol());
        assertEquals("9", pos.getPosUserValue());
        assertTrue(pos.isFilled());
    }
}
