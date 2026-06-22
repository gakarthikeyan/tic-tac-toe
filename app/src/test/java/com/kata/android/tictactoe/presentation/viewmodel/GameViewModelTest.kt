package com.kata.android.tictactoe.presentation.viewmodel

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.usecase.GameStateUseCase
import com.kata.android.tictactoe.domain.usecase.PlayMoveUseCase
import com.kata.android.tictactoe.domain.usecase.ResetGameUseCase
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GameViewModelTest {

    private val gameStateUseCase: GameStateUseCase = mockk()
    private val playMoveUseCase: PlayMoveUseCase = mockk()
    private val resetGameUseCase: ResetGameUseCase = mockk()
    private val viewModel = GameViewModel(
        gameStateUseCase = gameStateUseCase,
        playMoveUseCase = playMoveUseCase,
        resetGameUseCase = resetGameUseCase,
        dispatcher = Dispatchers.Unconfined
    )

    @Test
    fun `view model initializes with game state from use case`(): Unit = runBlocking {
        val gameBoardState = GameBoardState()
        coEvery { gameStateUseCase() } returns gameBoardState

        viewModel.initializeGame()
        val actualState = viewModel.gameBoardState.value
        assertEquals(gameBoardState, actualState)
    }

    @Test
    fun `updates game state when player move`(): Unit = runBlocking {
        val initialState = GameBoardState()
        val updatedState = initialState.playMove(CELL_POSITION_ZERO)
        coEvery { gameStateUseCase() } returns initialState
        coEvery { playMoveUseCase(CELL_POSITION_ZERO) } returns updatedState

        viewModel.initializeGame()
        viewModel.movePlayer(CELL_POSITION_ZERO)
        val actualState = viewModel.gameBoardState.value
        assertEquals(updatedState, actualState)
    }

    @Test
    fun `reset game clears the board`(): Unit = runBlocking {
        val newGameBoardState = GameBoardState()
        coEvery { resetGameUseCase() } returns newGameBoardState

        viewModel.resetGame()
        val actualState = viewModel.gameBoardState.value
        assertEquals(newGameBoardState, actualState)
    }
}