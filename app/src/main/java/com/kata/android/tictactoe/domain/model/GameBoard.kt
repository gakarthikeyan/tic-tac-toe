package com.kata.android.tictactoe.domain.model

import com.kata.android.tictactoe.utils.Constants.BOARD_CELL_COUNT

data class GameBoard(
    val cells: List<GamePlayer?> = MutableList(BOARD_CELL_COUNT) { null }
) {

    fun isCellEmpty(position: Int): Boolean = cells[position] == null

    fun markPlayerPlace(position: Int, gamePlayer: GamePlayer): GameBoard {
        if (!isCellEmpty(position)) return this

        val newCells = cells.toMutableList()
        newCells[position] = gamePlayer
        return GameBoard(newCells)
    }
}