package ru.stan.a65.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.repository.CharacterRepositoryImpl


@Module
class DomainModule {


    @Provides
    fun provideCharacterRepositoryImpl(application: Application,characterMapper: CharacterMapper): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(application, characterMapper)
    }
}