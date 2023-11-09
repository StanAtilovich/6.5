package ru.stan.a65.di

import dagger.Binds
import dagger.Module
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.repository.CharacterRepository

@Module
interface BindImpls {
    @Binds
    fun bindCharacterRepo(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository
}