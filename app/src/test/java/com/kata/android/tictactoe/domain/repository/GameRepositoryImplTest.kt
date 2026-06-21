package com.kata.android.tictactoe.domain.repository

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ONE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GameRepositoryImplTest {

    private val gameRepository: GameRepository = GameRepositoryImpl()

    @Test
    fun `initial game state is empty`(): Unit = runBlocking {
        val state = gameRepository.getGameState()
        TestCase.assertTrue(state.gameBoard.isBoardEmpty())
    }

    @Test
    fun `can save and retrieve game state`(): Unit = runBlocking {
        val gameBoardState = GameBoardState()
        val updatedState = gameBoardState.playMove(CELL_POSITION_ZERO)

        gameRepository.saveGameState(updatedState)
        val retrievedState = gameRepository.getGameState()

        TestCase.assertEquals(updatedState, retrievedState)
    }

    @Test
    fun `persists state across multiple operations`(): Unit = runBlocking {
        val gameBoardState = GameBoardState()
        var state = gameBoardState

        state = state.playMove(CELL_POSITION_ZERO)
        gameRepository.saveGameState(state)

        var retrievedState = gameRepository.getGameState()
        TestCase.assertEquals(state, retrievedState)

        state = retrievedState.playMove(CELL_POSITION_ONE)
        gameRepository.saveGameState(state)

        retrievedState = gameRepository.getGameState()
        TestCase.assertEquals(state, retrievedState)
    }

}