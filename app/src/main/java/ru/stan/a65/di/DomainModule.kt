package ru.stan.a65.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.repository.CharacterRepository


@Module
class DomainModule(
    private val application: Application
) {

    @Provides
    fun provideApplication(): Application{
        return application
    }

    @Provides
    fun provideCharacterRepositoryImpl(characterMapper: CharacterMapper): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(application, characterMapper)
    }

    @Provides
    fun provideCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository {
        return characterRepositoryImpl
    }
}