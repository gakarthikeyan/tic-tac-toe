package com.kata.android.tictactoe.domain.usecase

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ResetGameUseCaseTest {

    private val gameRepository: GameRepository = mockk()
    private val resetGameUseCase = ResetGameUseCase(gameRepository)

    @Test
    fun `resets game to initial state and saves it`(): Unit = runBlocking {
        val state = GameBoardState()
        coEvery { gameRepository.saveGameState(state) } returns Unit

        val result = resetGameUseCase()
        assertTrue(result.gameBoard.isBoardEmpty())
        assertEquals(GameBoardState(), result)
    }
}