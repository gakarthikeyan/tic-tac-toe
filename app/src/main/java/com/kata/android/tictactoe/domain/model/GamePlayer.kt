package com.kata.android.tictactoe.domain.model

enum class GamePlayer {
    X, O;

    fun nextPlayer(): GamePlayer = if (this == X) O else X
}