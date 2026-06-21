package com.kata.android.tictactoe.domain.model

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
}