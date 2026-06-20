package com.kata.android.tictactoe.domain.model

import kotlin.collections.contentEquals
import kotlin.collections.contentHashCode

/**
 * Game Tic-Tac-Toe with TDD approach.
 *
 * Game board model for check multiple approch
 * like fullfil the Board state casestudy requirements and cover all
 * possible scenarios.
 *
 * @author Karthikeyan G
 */
data class GameBoard(
    val cells: Array<GamePlayer?> = Array(9) { null }
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameBoard) return false
        return cells.contentEquals(other.cells)
    }

    override fun hashCode(): Int {
        return cells.contentHashCode()
    }

}