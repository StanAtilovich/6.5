package ru.stan.a65.di

import android.app.Application
import dagger.Module

@Module
class ContextModule(
    private val application: Application
) {
 //  @Provides
 //  fun provideContext(): Application {
 //      return application
 //  }
}