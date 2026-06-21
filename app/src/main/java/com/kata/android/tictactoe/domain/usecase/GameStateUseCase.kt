package com.kata.android.tictactoe.domain.usecase

import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.repository.GameRepository

class GameStateUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): GameBoardState {
        return gameRepository.getGameState()
    }
}