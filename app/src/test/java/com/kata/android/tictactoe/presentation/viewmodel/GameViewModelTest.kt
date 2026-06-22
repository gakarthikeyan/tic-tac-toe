package com.kata.android.tictactoe.presentation.viewmodel

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.usecase.GameStateUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GameViewModelTest {

    private val gameStateUseCase: GameStateUseCase = mockk()
    private val viewModel = GameViewModel(gameStateUseCase, Dispatchers.Unconfined)

    @Test
    fun `view model initializes with game state from use case`(): Unit = runBlocking {
        val gameBoardState = GameBoardState()
        coEvery { gameStateUseCase() } returns gameBoardState

        viewModel.initializeGame()
        assertEquals(gameBoardState, viewModel.gameBoardState.value)
    }

}