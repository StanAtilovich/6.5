package ru.stan.a65.di.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.stan.a65.data.paging.repoImpl.CharacterPagingRepositoryImpl
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.data.repository.ForumRepositoryImpl
import ru.stan.a65.domain.repository.CharacterPagingRepository
import ru.stan.a65.domain.repository.CharacterRepository
import ru.stan.a65.domain.repository.ForumRepository

@Module
@InstallIn(SingletonComponent::class)
interface BindImpls {
    @Binds
    fun bindCharacterRepo(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository


    @Binds
    fun bindForumRepository(
        forumRepositoryImpl: ForumRepositoryImpl
    ): ForumRepository


    @Binds
    fun bindCharacterPagingRepository(
        characterPagingRepositoryImpl: CharacterPagingRepositoryImpl
    ): CharacterPagingRepository
}