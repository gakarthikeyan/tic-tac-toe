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

        if (newBoard.hasWinningRow(currentPlayer) || newBoard.hasWinningColumn(currentPlayer)
            || newBoard.hasWinningDiagonal(currentPlayer)) {

            val gameResult = when (currentPlayer) {
                GamePlayer.X -> GameBoardResult.X_WINS
                GamePlayer.O -> GameBoardResult.O_WINS
            }
            return GameBoardState(
                gameBoard = newBoard,
                currentPlayer = currentPlayer,
                isGameOver = true,
                gameBoardResult = gameResult
            )
        }

        return GameBoardState(
            gameBoard = newBoard,
            currentPlayer = currentPlayer.nextPlayer(),
            isGameOver = false,
            gameBoardResult = GameBoardResult.ONGOING
        )
    }
}