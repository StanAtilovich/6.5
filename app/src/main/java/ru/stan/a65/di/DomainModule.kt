package ru.stan.a65.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.repository.CharacterRepository
import ru.stan.a65.domain.usecase.GetCharacterListUseCase
import ru.stan.a65.domain.usecase.GetCharacterUseCase


@Module
class DomainModule {

    @Provides
    fun provideCharacterRepositoryImpl(application: Application,characterMapper: CharacterMapper,characterDao: CharacterDao): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(application, characterMapper,characterDao)
    }

    @Provides
    fun provideGetCharacterListUseCase(
        characterRepository: CharacterRepository
    ): GetCharacterListUseCase {
        return GetCharacterListUseCase(characterRepository)
    }

    @Provides
    fun provideGetCharacterUseCase(
        characterRepository: CharacterRepository
    ): GetCharacterUseCase {
        return GetCharacterUseCase(characterRepository)
    }

}