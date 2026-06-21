package com.kata.android.tictactoe.domain.usecase

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.repository.GameRepository

class PlayMoveUseCase(private val gameRepository: GameRepository) {

    suspend operator fun invoke(position: Int): GameBoardState {
        val currentState = gameRepository.getGameState()
        val newState = currentState.playMove(position)
        gameRepository.saveGameState(newState)
        return newState
    }
}