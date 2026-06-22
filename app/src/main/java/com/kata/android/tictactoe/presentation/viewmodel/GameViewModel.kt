package com.kata.android.tictactoe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kata.android.tictactoe.domain.model.GameBoardState
import com.kata.android.tictactoe.domain.usecase.GameStateUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameStateUseCase: GameStateUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    private val _gameBoardState = MutableStateFlow(GameBoardState())
    val gameBoardState: StateFlow<GameBoardState> = _gameBoardState.asStateFlow()

    fun initializeGame() {
        viewModelScope.launch(dispatcher) {
            _gameBoardState.value = gameStateUseCase()
        }
    }
}