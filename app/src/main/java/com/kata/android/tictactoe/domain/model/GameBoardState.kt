package com.kata.android.tictactoe.domain.model

data class GameBoardState(
    val gameBoard: GameBoard = GameBoard(),
    val currentPlayer: GamePlayer = GamePlayer.X,
    val isGameOver: Boolean = false,
    val gameBoardResult: GameBoardResult = GameBoardResult.ONGOING
) {

    fun playMove(position: Int): GameBoardState {
        if (isGameOver) return this
        if (!gameBoard.isCellEmpty(position)) return this

        val newBoard = gameBoard.markPlayerPlace(position, currentPlayer)
        return GameBoardState(
            gameBoard = newBoard,
            currentPlayer = if (currentPlayer == GamePlayer.X) GamePlayer.O else GamePlayer.X,
            isGameOver = false,
            gameBoardResult = GameBoardResult.ONGOING
        )
    }
}