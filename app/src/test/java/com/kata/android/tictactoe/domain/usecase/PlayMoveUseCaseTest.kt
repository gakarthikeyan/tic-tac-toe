package com.kata.android.tictactoe.domain.usecase

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.model.GamePlayer
import com.kata.android.tictactoe.domain.repository.GameRepository
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PlayMoveUseCaseTest {

    private val gameRepository: GameRepository = mockk()
    private val playMoveUseCase = PlayMoveUseCase(gameRepository)

    @Test
    fun `play move on empty board places X mark`(): Unit = runBlocking {
        val initialState = GameBoardState()
        val expectedState = initialState.playMove(CELL_POSITION_ZERO)

        coEvery { gameRepository.getGameState() } returns initialState

        val result = playMoveUseCase(CELL_POSITION_ZERO)
        assertEquals(expectedState.gameBoard.cells[CELL_POSITION_ZERO], GamePlayer.X)
        assertEquals(GamePlayer.O, result.currentPlayer)
    }
}