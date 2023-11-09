package ru.stan.a65.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.data.mapper.CharacterMapper

@Module
class DataModule {
    @Provides
    fun provideMapperForCharacterRepositoryImpl(): CharacterMapper{
        return CharacterMapper()
    }

    @Provides
    fun provideCharacterDao(application: Application):CharacterDao{
        return CharacterDatabase.getInstance(application).characterDao()
    }


}