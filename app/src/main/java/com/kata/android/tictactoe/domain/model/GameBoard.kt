package com.kata.android.tictactoe.domain.model

import com.kata.android.tictactoe.utils.Constants.BOARD_CELL_COUNT
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_EIGHT
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_FOUR
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_SIX
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_TWO
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO

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

    fun hasWinningColumn(gamePlayer: GamePlayer): Boolean {
        // Columns: 0-3-6, 1-4-7, 2-5-8
        for (col in 0..2) {
            if (cells[col] == gamePlayer && cells[col + 3] == gamePlayer && cells[col + 6] == gamePlayer) {
                return true
            }
        }
        return false
    }

    fun hasWinningDiagonal(gamePlayer: GamePlayer): Boolean {
        // diagonal: 0-4-8
        if (cells[CELL_POSITION_ZERO] == gamePlayer && cells[CELL_POSITION_FOUR] == gamePlayer
            && cells[CELL_POSITION_EIGHT] == gamePlayer) {
            return true
        }
        // diagonal: 2-4-6
        if (cells[CELL_POSITION_TWO] == gamePlayer && cells[CELL_POSITION_FOUR] == gamePlayer
            && cells[CELL_POSITION_SIX] == gamePlayer) {
            return true
        }
        return false
    }

    fun isBoardEmpty(): Boolean = cells.all { it == null }
}