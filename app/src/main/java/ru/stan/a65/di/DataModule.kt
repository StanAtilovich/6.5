package ru.stan.a65.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.local.database.CharacterDatabase

@Module
class DataModule {
 //   @Provides
 //   fun provideMapperForCharacterRepositoryImpl(): CharacterMapper{
 //       return CharacterMapper()
 //   }

    @Provides
    fun provideCharacterDao(application: Application):CharacterDao{
        return CharacterDatabase.getInstance(application).characterDao()
    }
 //   @Provides
  //  fun provideCharacterPagingMapper(): CharacterPagingMapper{
  //      return CharacterPagingMapper()
  //  }

//   @Provides
//   fun provideCharacterPagingSource(characterPagingMapper: CharacterPagingMapper): CharacterPagingSource{
//       return CharacterPagingSource(characterPagingMapper)
//   }
//
  //  @Provides
  //  fun provideCharacterPagingRepositoryImpl(
  //      characterPagingSource: CharacterPagingSource
  //  ): CharacterPagingRepositoryImpl {
  //      return CharacterPagingRepositoryImpl(characterPagingSource)
  //  }

 //  @Provides
 //  fun provideCharacterRepositoryImpl(
 //      application: Application,
 //      characterMapper: CharacterMapper,
 //      characterDao: CharacterDao
 //  ): CharacterRepositoryImpl {
 //      return CharacterRepositoryImpl(application, characterMapper, characterDao)
 //  }

}