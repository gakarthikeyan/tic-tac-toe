package com.kata.android.tictactoe.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.kata.android.tictactoe.R
import com.kata.android.tictactoe.domain.model.GameBoardResult
import com.kata.android.tictactoe.domain.model.GamePlayer
import com.kata.android.tictactoe.presentation.viewmodel.GameViewModel
import com.kata.android.tictactoe.utils.Dimens.dimen_16dp
import com.kata.android.tictactoe.utils.Dimens.dimen_4dp
import com.kata.android.tictactoe.utils.Dimens.dimen_8dp
import com.kata.android.tictactoe.utils.FontSize.font_18sp
import com.kata.android.tictactoe.utils.FontSize.font_28sp
import com.kata.android.tictactoe.utils.FontSize.font_32sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<GameViewModel>()
    val gameState = viewModel.gameBoardState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initializeGame()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimen_16dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.game_title),
                fontSize = font_28sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(dimen_8dp))
            if (gameState.value.isGameOver) {
                Text(
                    text = when (gameState.value.gameBoardResult) {
                        GameBoardResult.X_WINS -> stringResource(R.string.player_x_win)
                        else -> ""
                    },
                    fontSize = font_18sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = stringResource(R.string.label_current_player, gameState.value.currentPlayer),
                    fontSize = font_18sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        GameBoard(
            board = gameState.value.gameBoard.cells,
            onClick = { position -> viewModel.movePlayer(position) }
        )
    }
}

@Composable
fun GameBoard(
    board: List<GamePlayer?>,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(dimen_16dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (row in 0..2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (col in 0..2) {
                    val index = row * 3 + col
                    GameCell(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(dimen_4dp),
                        gamePlayer = board[index],
                        onClick = { onClick(index) }
                    )
                }
            }
        }
    }
}

@Composable
fun GameCell(
    modifier: Modifier = Modifier,
    gamePlayer: GamePlayer?,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .semantics { contentDescription = "" }
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            )
            .clickable(onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {
        gamePlayer?.let {
            Text(
                text = gamePlayer.name,
                fontSize = font_32sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = when (gamePlayer) {
                    GamePlayer.X -> MaterialTheme.colorScheme.primary
                    GamePlayer.O -> MaterialTheme.colorScheme.tertiary
                },
            )
        }
    }
}