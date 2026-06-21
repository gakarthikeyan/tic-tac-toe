package com.kata.android.tictactoe.di

import com.kata.android.tictactoe.domain.repository.GameRepository
import com.kata.android.tictactoe.domain.repository.GameRepositoryImpl
import org.koin.dsl.module

val gameModule = module {
    single<GameRepository> { GameRepositoryImpl() }
}
