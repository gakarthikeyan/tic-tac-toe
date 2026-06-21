package com.kata.android.tictactoe.domain.model

data class GameBoardState(
    val gameBoard: GameBoard = GameBoard(),
    val currentPlayer: GamePlayer = GamePlayer.X,
    val isGameOver: Boolean = false,
    val gameBoardResult: GameBoardResult = GameBoardResult.ONGOING
)