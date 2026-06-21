package com.kata.android.tictactoe.domain.model

import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_EIGHT
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_FIVE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_FOUR
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ONE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_SEVEN
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_SIX
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_THREE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_TWO
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import kotlin.collections.get

class GameBoardStateTest {

    @Test
    fun `game starts with X player and empty board`() {
        val gameBoardState = GameBoardState()
        assertEquals(GamePlayer.X, gameBoardState.currentPlayer)
        assertTrue(gameBoardState.gameBoard.isBoardEmpty())
        assertFalse(gameBoardState.isGameOver)
        assertEquals(GameBoardResult.ONGOING, gameBoardState.gameBoardResult)
    }

    @Test
    fun `after X moves, it's O's turn`() {
        val gameBoardState = GameBoardState()
        val newGameState = gameBoardState.playMove(CELL_POSITION_ZERO)
        assertEquals(GamePlayer.O, newGameState.currentPlayer)
    }

    @Test
    fun `after O moves, it's X's turn`() {
        val gameBoardState = GameBoardState()
        var newGameState = gameBoardState.playMove(CELL_POSITION_ZERO)
        newGameState = newGameState.playMove(CELL_POSITION_ONE)
        assertEquals(GamePlayer.X, newGameState.currentPlayer)
    }

    @Test
    fun `game is over when player wins with row`() {
        val gameBoardState = GameBoardState()
        var state = gameBoardState

        state = state.playMove(CELL_POSITION_ZERO)
        state = state.playMove(CELL_POSITION_THREE)
        state = state.playMove(CELL_POSITION_ONE)
        state = state.playMove(CELL_POSITION_FOUR)
        state = state.playMove(CELL_POSITION_TWO)

        assertTrue(state.isGameOver)
        assertEquals(GameBoardResult.X_WINS, state.gameBoardResult)
    }

    @Test
    fun `game is over when player wins with column`() {
        val gameBoardState = GameBoardState()
        var state = gameBoardState

        state = state.playMove(CELL_POSITION_ZERO)
        state = state.playMove(CELL_POSITION_ONE)
        state = state.playMove(CELL_POSITION_THREE)
        state = state.playMove(CELL_POSITION_FOUR)
        state = state.playMove(CELL_POSITION_TWO)
        state = state.playMove(CELL_POSITION_SEVEN)

        assertTrue(state.isGameOver)
        assertEquals(GameBoardResult.O_WINS, state.gameBoardResult)
    }

    @Test
    fun `game is over when player wins with diagonal`() {
        val gameBoardState = GameBoardState()
        var state = gameBoardState

        state = state.playMove(CELL_POSITION_ZERO)
        state = state.playMove(CELL_POSITION_ONE)
        state = state.playMove(CELL_POSITION_FOUR)
        state = state.playMove(CELL_POSITION_TWO)
        state = state.playMove(CELL_POSITION_EIGHT)

        assertTrue(state.isGameOver)
        assertEquals(GameBoardResult.X_WINS, state.gameBoardResult)
    }

    @Test
    fun `game is draw when board is full and no winner`() {
        val gameBoardState = GameBoardState()
        var state = gameBoardState

        state = state.playMove(CELL_POSITION_ZERO)
        state = state.playMove(CELL_POSITION_ONE)
        state = state.playMove(CELL_POSITION_TWO)
        state = state.playMove(CELL_POSITION_THREE)
        state = state.playMove(CELL_POSITION_FIVE)
        state = state.playMove(CELL_POSITION_FOUR)
        state = state.playMove(CELL_POSITION_SIX)
        state = state.playMove(CELL_POSITION_EIGHT)
        state = state.playMove(CELL_POSITION_SEVEN)

        assertTrue(state.isGameOver)
        assertEquals(GameBoardResult.DRAW, state.gameBoardResult)
    }

    @Test
    fun `cannot play move on occupied cell`() {
        val gameBoardState = GameBoardState()
        var state = gameBoardState
        state = state.playMove(CELL_POSITION_ZERO)
        state = state.playMove(CELL_POSITION_ZERO)

        assertEquals(GamePlayer.O, state.currentPlayer)
    }

    @Test
    fun `game state is updated payer move and game board is updated`() {
        val gameBoardState = GameBoardState()
        val newGameState = gameBoardState.playMove(CELL_POSITION_FOUR)  

        assertEquals(GamePlayer.O, newGameState.currentPlayer)
        assertEquals(GamePlayer.X, newGameState.gameBoard.cells[CELL_POSITION_FOUR])
        assertFalse(newGameState.isGameOver)
        assertEquals(GameBoardResult.ONGOING, newGameState.gameBoardResult)
    }
}