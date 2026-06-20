package com.kata.android.tictactoe.domain.model

import com.kata.android.tictactoe.utils.Constants.BOARD_CELL_COUNT
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ONE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_TWO
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class GameBoardTest {

    @Test
    fun `board is created with 9 empty cells`() {
        val gameBoard = GameBoard()
        assertEquals(BOARD_CELL_COUNT, gameBoard.cells.size)
        assertTrue(gameBoard.cells.all { it == null })
    }

    @Test
    fun `can place player symbol on empty cell`() {
        val gameBoard = GameBoard()
        val newBoard = gameBoard.markPlayerPlace(CELL_POSITION_ZERO, GamePlayer.X)
        assertEquals(GamePlayer.X, newBoard.cells[CELL_POSITION_ZERO])
    }

    @Test
    fun `cell is empty when position is null`() {
        val gameBoard = GameBoard()
        assertTrue(gameBoard.isCellEmpty(CELL_POSITION_ONE))
    }

    @Test
    fun `cell is not empty when occupied`() {
        val gameBoard = GameBoard()
        val newBoard = gameBoard.markPlayerPlace(CELL_POSITION_ONE, GamePlayer.O)
        assertFalse(newBoard.isCellEmpty(CELL_POSITION_ONE))
    }

    @Test
    fun `board is full when all cells are occupied`() {
        val gameBoard = GameBoard(
            cells = MutableList(BOARD_CELL_COUNT) {
                GamePlayer.X; GamePlayer.O; GamePlayer.X;
                GamePlayer.O; GamePlayer.X; GamePlayer.O;
                GamePlayer.X; GamePlayer.O; GamePlayer.X
            }
        )
        assertTrue(gameBoard.isBoardFull())
    }

    @Test
    fun `board is not full when there are empty cells`() {
        val gameBoard = GameBoard()
        assertFalse(gameBoard.isBoardFull())
    }

    @Test
    fun `cannot place mark on already occupied cell`() {
        val gameBoard = GameBoard()
        val newBoard = gameBoard.markPlayerPlace(CELL_POSITION_TWO, GamePlayer.X)
        val result = newBoard.markPlayerPlace(CELL_POSITION_TWO, GamePlayer.O)
        assertEquals(newBoard, result)
    }

    @Test
    fun `can check if player has winning row`() {
        val cells = MutableList<GamePlayer?>(BOARD_CELL_COUNT)  { null }
        cells[CELL_POSITION_ZERO] = GamePlayer.X
        cells[CELL_POSITION_ONE] = GamePlayer.X
        cells[CELL_POSITION_TWO] = GamePlayer.X
        val gameBoard = GameBoard(cells)
        assertTrue(gameBoard.hasWinningRow(GamePlayer.X))
    }
}