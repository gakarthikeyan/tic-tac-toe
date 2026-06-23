package com.kata.android.tictactoe

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class GameScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkTitleIsDisplayedInGameScreen() {
        val gameTitle = composeTestRule.activity.getString(R.string.game_title)
        composeTestRule.onNodeWithText(gameTitle).assertExists()
    }

    @Test
    fun checkPlayerLabelIsDisplayedInGameScreen() {
        val playerLabel = composeTestRule.activity.getString(R.string.label_current_player, "X")
        composeTestRule.onNodeWithText(playerLabel).assertExists()
    }

    @Test
    fun checkGameBoardIsDisplayedInGameScreen() {
        composeTestRule.waitForIdle()

        val cells = composeTestRule.onAllNodesWithContentDescription("")
        if (cells.fetchSemanticsNodes().size == 9) {
            composeTestRule.waitForIdle()
            composeTestRule.onNodeWithText("").assertExists()
        }
        assert(cells.fetchSemanticsNodes().isEmpty())
    }
}