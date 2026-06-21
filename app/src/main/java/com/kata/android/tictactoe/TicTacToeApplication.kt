package com.kata.android.tictactoe

import android.app.Application
import com.kata.android.tictactoe.di.gameModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TicTacToeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicTacToeApplication)
            modules(gameModule)
        }
    }
}
