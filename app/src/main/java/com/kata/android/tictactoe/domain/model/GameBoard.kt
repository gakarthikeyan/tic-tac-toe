package com.kata.android.tictactoe.domain.model

import android.util.Log
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

    fun isBoardFull(): Boolean = cells.all { it != null }

    fun hasWinningRow(gamePlayer: GamePlayer): Boolean {
        // Rows: 0-1-2, 3-4-5, 6-7-8
        for (row in 0..2) {
            val start = row * 3
            if (cells[start] == gamePlayer && cells[start + 1] == gamePlayer && cells[start + 2] == gamePlayer) {
                return true
            }
        }
        return false
    }
}