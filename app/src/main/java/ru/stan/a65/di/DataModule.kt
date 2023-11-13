package ru.stan.a65.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.paging.mapper.CharacterPagingMapper
import ru.stan.a65.data.paging.pagingSource.CharacterPagingSource
import ru.stan.a65.data.paging.repoImpl.CharacterPagingRepositoryImpl

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
    @Provides
    fun provideCharacterPagingMapper(): CharacterPagingMapper{
        return CharacterPagingMapper()
    }

    @Provides
    fun provideCharacterPagingSource(characterPagingMapper: CharacterPagingMapper): CharacterPagingSource{
        return CharacterPagingSource(characterPagingMapper)
    }

    @Provides
    fun provideCharacterPagingRepositoryImpl(
        characterPagingSource: CharacterPagingSource
    ): CharacterPagingRepositoryImpl {
        return CharacterPagingRepositoryImpl(characterPagingSource)
    }

}