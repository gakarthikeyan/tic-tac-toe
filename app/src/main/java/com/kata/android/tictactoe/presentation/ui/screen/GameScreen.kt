package com.kata.android.tictactoe.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kata.android.tictactoe.R
import com.kata.android.tictactoe.presentation.viewmodel.GameViewModel
import com.kata.android.tictactoe.utils.Dimens.dimen_8dp
import com.kata.android.tictactoe.utils.FontSize.font_28sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<GameViewModel>()
    val gameState = viewModel.gameBoardState.collectAsState()

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
        Text(
            text = stringResource(R.string.label_current_player, gameState.value.currentPlayer),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}