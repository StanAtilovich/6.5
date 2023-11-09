package ru.stan.a65.di

import dagger.Module
import dagger.Provides
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.GetCharacterListUseCase
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListCharactersViewModel
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListViewModelFactory

@Module
class PresentationModule {

    @Provides
    fun provideGetCharacterListUseCase(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): GetCharacterListUseCase {
        return GetCharacterListUseCase(characterRepositoryImpl)
    }

    @Provides
    fun provideCharacterListViewModel(characterRepositoryImpl: CharacterRepositoryImpl): ListCharactersViewModel{
      return ListCharactersViewModel(provideGetCharacterListUseCase(characterRepositoryImpl))
    }

    @Provides
    fun provideCharacterListViewModelFactory(listCharactersViewModel: ListCharactersViewModel):ListViewModelFactory{
        return ListViewModelFactory(listCharactersViewModel)
    }
}