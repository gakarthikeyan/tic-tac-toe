package com.kata.android.tictactoe

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelProvider
import com.kata.android.tictactoe.presentation.viewmodel.GameViewModel
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_EIGHT
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_FIVE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_FOUR
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ONE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_SEVEN
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_SIX
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_THREE
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_TWO
import com.kata.android.tictactoe.utils.Constants.CELL_POSITION_ZERO
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        viewModel =  ViewModelProvider(composeTestRule.activity)[GameViewModel::class.java]
        viewModel.initializeGame()
    }

    @After
    fun tearDown() {
        viewModel.resetGame()
    }

    @Test
    fun checkTitleIsDisplayedInGameScreen() {
        composeTestRule.waitForIdle()
        val gameTitle = composeTestRule.activity.getString(R.string.game_title)
        composeTestRule.onNodeWithText(gameTitle).assertExists()
    }

    @Test
    fun checkPlayerLabelIsDisplayedInGameScreen() {
        composeTestRule.waitForIdle()
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

    @Test
    fun checkCurrentAndOpponentPlayerPlacedOnCell(){
        composeTestRule.waitForIdle()
        val cells = composeTestRule.onAllNodesWithContentDescription("")

        cells[CELL_POSITION_ZERO].performClick()
        composeTestRule.onNodeWithText("X").assertExists()

        cells[CELL_POSITION_ONE].performClick()
        composeTestRule.onNodeWithText("O").assertExists()
    }

    @Test
    fun checkBoardContainsEmptyCellInOngoingMove() {
        composeTestRule.waitForIdle()
        val cells = composeTestRule.onAllNodesWithContentDescription("")
        val nodesList = cells.fetchSemanticsNodes()
        for(i in 0..2){
            cells[i].performClick()
            composeTestRule.waitForIdle()
        }

        val count = nodesList.count { node ->
            val text = node.config
                .getOrNull(SemanticsProperties.Text)
                ?.joinToString("") { it.text }
                .orEmpty()

            text.isBlank()
        }

        assertTrue(count > 0)
    }

    @Test
    fun checkPlayer_X_WonTheGame(){
        composeTestRule.waitForIdle()
        val cells = composeTestRule.onAllNodesWithContentDescription("")
        cells[CELL_POSITION_ZERO].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_ONE].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_THREE].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_TWO].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_SIX].performClick()
        composeTestRule.waitForIdle()

        val xWon = composeTestRule.activity.getString(R.string.player_x_win)
        composeTestRule.onNodeWithText(xWon).assertExists()
    }

    @Test
    fun checkPlayer_O_WonTheGame(){
        composeTestRule.waitForIdle()
        val cells = composeTestRule.onAllNodesWithContentDescription("")
        cells[CELL_POSITION_ZERO].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_ONE].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_THREE].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_FOUR].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_TWO].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_SEVEN].performClick()
        composeTestRule.waitForIdle()

        val oWon = composeTestRule.activity.getString(R.string.player_o_win)
        composeTestRule.onNodeWithText(oWon).assertExists()
    }

    @Test
    fun checkGameEndsWithDraw(){
        composeTestRule.waitForIdle()
        val cells = composeTestRule.onAllNodesWithContentDescription("")
        cells[CELL_POSITION_ZERO].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_ONE].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_TWO].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_FOUR].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_SEVEN].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_THREE].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_FIVE].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_EIGHT].performClick()
        composeTestRule.waitForIdle()
        cells[CELL_POSITION_SIX].performClick()
        composeTestRule.waitForIdle()

        val gameDraw = composeTestRule.activity.getString(R.string.game_draw)
        composeTestRule.onNodeWithText(gameDraw).assertExists()
    }

    @Test
    fun checkResetButtonIsDisplayed(){
        composeTestRule.waitForIdle()
        val resetGame = composeTestRule.activity.getString(R.string.reset_game)
        composeTestRule.onNodeWithText(resetGame).assertExists()
    }
}