package ru.stan.a65.di

import dagger.Module
import dagger.Provides
import ru.stan.a65.domain.repository.CharacterRepository
import ru.stan.a65.domain.usecase.GetCharacterListUseCase
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListCharactersViewModel
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListViewModelFactory

@Module
class PresentationModule {
    @Provides
    fun provideGetCharacterListUseCase(
        characterRepository: CharacterRepository
    ): GetCharacterListUseCase {
        return GetCharacterListUseCase(characterRepository)
    }

    @Provides
    fun provideCharacterListViewModel(
        characterListUseCase: GetCharacterListUseCase):
            ListCharactersViewModel{
      return ListCharactersViewModel(
          characterListUseCase
      )
    }

    @Provides
    fun provideCharacterListViewModelFactory(listCharactersViewModel: ListCharactersViewModel):ListViewModelFactory{
        return ListViewModelFactory(listCharactersViewModel)
    }
}