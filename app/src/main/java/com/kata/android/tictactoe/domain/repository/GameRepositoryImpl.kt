package com.kata.android.tictactoe.domain.repository

import com.kata.android.tictactoe.domain.model.GameBoardState

class GameRepositoryImpl : GameRepository {

    private var gameBoardState: GameBoardState = GameBoardState()

    override suspend fun getGameState(): GameBoardState {
        return gameBoardState
    }

    override suspend fun saveGameState(gameBoardState: GameBoardState) {
        this.gameBoardState = gameBoardState
    }
}