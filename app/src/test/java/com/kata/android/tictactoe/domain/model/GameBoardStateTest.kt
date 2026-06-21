package com.kata.android.tictactoe.domain.model

import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_FOUR
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ONE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_THREE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_TWO
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

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
}