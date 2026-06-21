package com.kata.android.tictactoe.di

import com.kata.android.tictactoe.domain.repository.GameRepository
import com.kata.android.tictactoe.domain.repository.GameRepositoryImpl
import com.kata.android.tictactoe.domain.usecase.GameStateUseCase
import com.kata.android.tictactoe.domain.usecase.PlayMoveUseCase
import org.koin.dsl.module

val gameModule = module {
    single<GameRepository> { GameRepositoryImpl() }

    factory { GameStateUseCase(get()) }
    factory { PlayMoveUseCase(get()) }
}
