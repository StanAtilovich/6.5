package ru.stan.a65.di.hilt

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.local.database.CharacterDatabase

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {
    @Provides
    fun provideCharacterDao(application: Application): CharacterDao {
        return CharacterDatabase.getInstance(application).characterDao()
    }
}