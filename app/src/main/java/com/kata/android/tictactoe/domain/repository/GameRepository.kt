package com.kata.android.tictactoe.domain.repository

import com.kata.android.tictactoe.domain.model.GameBoardState

interface GameRepository {
    suspend fun getGameState(): GameBoardState
    suspend fun saveGameState(gameBoardState: GameBoardState)
}