package com.kata.android.tictactoe

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
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
        assert(cells.fetchSemanticsNodes().isNotEmpty())
        assert(cells.fetchSemanticsNodes().size >= 9)
    }

    @Test
    fun checkAllNineCellsAreClickable() {
        composeTestRule.waitForIdle()

        val cells = composeTestRule.onAllNodesWithContentDescription("")
        val nodesList = cells.fetchSemanticsNodes()
        nodesList.forEachIndexed { index, _ -> cells[index].performClick() }
        assert(nodesList.size >= 9) { "Expected at least 9 cells, found ${nodesList.size}" }
    }

    @Test
    fun checkAbleToPlacePlayerOnCell(){
        composeTestRule.waitForIdle()
        val cells = composeTestRule.onAllNodesWithContentDescription("")
        cells[CELL_POSITION_ZERO].performClick()
        composeTestRule.onNodeWithText("X").assertExists()
    }
}