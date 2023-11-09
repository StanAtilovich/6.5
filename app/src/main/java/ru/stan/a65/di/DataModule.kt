package ru.stan.a65.di

import dagger.Module
import dagger.Provides
import ru.stan.a65.data.mapper.CharacterMapper

@Module
class DataModule {
    @Provides
    fun provideMapperForCharacterRepositoryImpl(): CharacterMapper{
        return CharacterMapper()
    }


}