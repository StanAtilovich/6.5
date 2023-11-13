package ru.stan.a65.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.paging.pagingSource.CharacterPagingSource
import ru.stan.a65.data.paging.repoImpl.CharacterPagingRepositoryImpl
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.repository.CharacterPagingRepository
import ru.stan.a65.domain.repository.CharacterRepository
import ru.stan.a65.domain.usecase.CashCharacterListUseCase
import ru.stan.a65.domain.usecase.GetCharacterListUseCase
import ru.stan.a65.domain.usecase.GetCharacterUseCase
import ru.stan.a65.domain.usecase.GetPagerForCharactersUseCase
import ru.stan.a65.domain.usecase.UploadListUseCase


@Module
class DomainModule {

    @Provides
    fun provideCharacterRepositoryImpl(
        characterMapper: CharacterMapper,
        characterDao: CharacterDao
    ): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(application = Application(), characterMapper, characterDao)
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

    @Provides
    fun provideUploadListUseCase(
        characterRepository: CharacterRepository
    ): UploadListUseCase {
        return UploadListUseCase(characterRepository)
    }

    @Provides
    fun provideCashCharacterListUseCase(
        characterRepository: CharacterRepository
    ): CashCharacterListUseCase {
        return CashCharacterListUseCase(characterRepository)
    }

    @Provides
    fun provideGetPagerForCharactersUseCase(
        characterPagingRepository: CharacterPagingRepository
    ): GetPagerForCharactersUseCase{
        return GetPagerForCharactersUseCase(characterPagingRepository)
    }

}