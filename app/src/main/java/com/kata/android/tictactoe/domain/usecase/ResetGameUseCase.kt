package com.kata.android.tictactoe.domain.usecase

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.repository.GameRepository

class ResetGameUseCase(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): GameBoardState {
        val newState = GameBoardState()
        gameRepository.saveGameState(newState)
        return newState
    }
}