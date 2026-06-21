package com.kata.android.tictactoe.domain.repository

import com.kata.android.tictactoe.domain.model.GameBoardState

class GameRepositoryImpl(): GameRepository {

    private val gameBoardState: GameBoardState = GameBoardState()

    override suspend fun getGameState(): GameBoardState {
        return gameBoardState
    }
}