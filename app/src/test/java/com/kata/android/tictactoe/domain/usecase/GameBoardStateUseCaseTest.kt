package com.kata.android.tictactoe.domain.usecase

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GameBoardStateUseCaseTest {

    private val gameRepository: GameRepository = mockk()
    private val gameStateUseCase = GameStateUseCase(gameRepository)

    @Test
    fun `return current game state from repository`(): Unit = runBlocking {
        val gameBoardState = GameBoardState()
        coEvery { gameRepository.getGameState() } returns gameBoardState

        val result = gameStateUseCase()
        assertEquals(gameBoardState, result)
    }
}