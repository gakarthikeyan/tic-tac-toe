package com.kata.android.tictactoe.domain.repository

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

}