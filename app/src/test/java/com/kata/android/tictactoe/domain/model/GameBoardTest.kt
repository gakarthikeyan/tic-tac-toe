package com.kata.android.tictactoe.domain.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

/**
 * Game Tic-Tac-Toe with TDD approach.
 *
 * Test all the cells of the board. Check if a player has won,
 * if the board is full, or if the game is still ongoing.
 * Already occupied cases, etc.
 *
 * @author Karthikeyan G
 */
class GameBoardTest {

    @Test
    fun `board is created with 9 empty cells`() {
        val gameBoard = GameBoard()
        assertEquals(9, gameBoard.cells.size)
        assertTrue(gameBoard.cells.all { it == null })
    }
}